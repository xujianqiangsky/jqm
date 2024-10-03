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
import plus.jqm.admin.mapper.SysUserRoleMapper;
import plus.jqm.admin.service.SysUserRoleService;
import plus.jqm.api.domain.SysUserRole;
import plus.jqm.api.domain.dto.SysUserRoleDTO;
import plus.jqm.api.domain.vo.SysUserRoleVO;
import plus.jqm.common.core.constant.cache.CacheConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户角色业务逻辑接口实现
 *
 * @author xujianqiang
 * @date 2024/09/27
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public IPage<SysUserRoleVO> listRelations(long pageNum, long pageSize) {
        IPage<SysUserRole> userRolePage = new Page<>(pageNum, pageSize);
        baseMapper.selectPage(userRolePage);
        IPage<SysUserRoleVO> userRoleVOPage = new Page<>();
        BeanUtils.copyProperties(userRolePage, userRoleVOPage, "records");
        List<SysUserRoleVO> userRoleVOList = new ArrayList<>();
        for (SysUserRole userRole : userRolePage.getRecords()) {
            SysUserRoleVO userRoleVO = new SysUserRoleVO();
            BeanUtils.copyProperties(userRole, userRoleVO);
            userRoleVOList.add(userRoleVO);
        }
        userRoleVOPage.setRecords(userRoleVOList);
        return userRoleVOPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public void saveUserRole(List<SysUserRoleDTO> userRoleDTOList) {
        List<SysUserRole> userRoleList = userRoleDTOList.stream()
                .map(userRoleDTO -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    userRoleDTO.setId(null);
                    BeanUtils.copyProperties(userRoleDTO, sysUserRole);
                    return sysUserRole;
                })
                .toList();
        saveBatch(userRoleList);

        // 清除缓存角色
        Map<Long, List<SysUserRoleDTO>> userRoleDTOMap = userRoleDTOList.stream()
                .collect(Collectors.groupingBy(SysUserRoleDTO::getUserId));
        userRoleDTOMap.forEach((userId, list) -> {
            List<String> roleList = (List<String>) SaManager.getSaTokenDao()
                    .getObject(CacheConstants.ROLE_CACHE_KEY_PREFIX.getKey() + userId);
            if (roleList != null) {
                SaManager.getSaTokenDao()
                        .setObject(CacheConstants.ROLE_CACHE_KEY_PREFIX.getKey() + userId, null, StpUtil.getTokenTimeout());
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void removeUserRoleById(Long id) {
        SysUserRole userRole = baseMapper.selectUserRoleById(id);
        removeById(id);
        if (userRole != null) {
            List<String> roleList = (List<String>) SaManager.getSaTokenDao()
                    .getObject(CacheConstants.ROLE_CACHE_KEY_PREFIX.getKey() + userRole.getUserId());
            if (roleList != null) {
                roleList.removeIf(role -> role.equals(userRole.getRoleName()));
                SaManager.getSaTokenDao()
                        .setObject(CacheConstants.ROLE_CACHE_KEY_PREFIX.getKey() + userRole.getUserId(), roleList, StpUtil.getTokenTimeout());
            }
        }
    }
}
