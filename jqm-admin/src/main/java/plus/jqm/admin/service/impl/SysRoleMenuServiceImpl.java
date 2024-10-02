package plus.jqm.admin.service.impl;

/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.jqm.admin.mapper.SysRoleMenuMapper;
import plus.jqm.admin.service.SysRoleMenuService;
import plus.jqm.api.domain.SysRoleMenu;
import plus.jqm.api.domain.dto.SysRoleMenuDTO;
import plus.jqm.api.domain.vo.SysRoleMenuVO;
import plus.jqm.common.core.constant.cache.CacheConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户菜单业务逻辑实现
 *
 * @author xujianqiang
 * @date 2024/09/27
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public IPage<SysRoleMenuVO> listRelations(long pageNum, long pageSize) {
        IPage<SysRoleMenu> roleMenuPage = new Page<>(pageNum, pageSize);
        baseMapper.selectPage(roleMenuPage);
        IPage<SysRoleMenuVO> roleMenuVOPage = new Page<>();
        BeanUtils.copyProperties(roleMenuPage, roleMenuVOPage, "records");
        List<SysRoleMenuVO> userRoleVOList = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenuPage.getRecords()) {
            SysRoleMenuVO roleMenuVO = new SysRoleMenuVO();
            BeanUtils.copyProperties(roleMenu, roleMenuVO);
            userRoleVOList.add(roleMenuVO);
        }
        roleMenuVOPage.setRecords(userRoleVOList);
        return roleMenuVOPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public void saveRoleMenu(List<SysRoleMenuDTO> roleMenuDTOList) {
        List<SysRoleMenu> roleMenuList = roleMenuDTOList.stream()
                .map(roleMenuDTO -> {
                    SysRoleMenu roleMenu = new SysRoleMenu();
                    roleMenuDTO.setId(null);
                    BeanUtils.copyProperties(roleMenuDTO, roleMenu);
                    return roleMenu;
                })
                .toList();
        saveBatch(roleMenuList);

        // 更新缓存权限
        Map<String, List<SysRoleMenuDTO>> roleMenuDTOMap = roleMenuDTOList.stream()
                .collect(Collectors.groupingBy(SysRoleMenuDTO::getRoleName));
        roleMenuDTOMap.forEach((roleId, list) -> {
            List<String> permissionList = (List<String>) SaManager.getSaTokenDao()
                    .getObject(CacheConstants.PERMISSION_CACHE_KEY_PREFIX.getKey() + roleId);
            if (permissionList != null) {
                List<String> newPermissionList = list.stream()
                        .map(SysRoleMenuDTO::getPermission)
                        .toList();
                permissionList.addAll(newPermissionList);
                SaManager.getSaTokenDao()
                        .setObject(CacheConstants.PERMISSION_CACHE_KEY_PREFIX.getKey() + roleId, permissionList, StpUtil.getTokenTimeout());
            }
        });
    }
}
