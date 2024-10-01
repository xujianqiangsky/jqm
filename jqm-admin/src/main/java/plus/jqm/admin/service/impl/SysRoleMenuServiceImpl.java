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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.jqm.admin.mapper.SysRoleMenuMapper;
import plus.jqm.admin.service.SysRoleMenuService;
import plus.jqm.api.domain.SysRoleMenu;
import plus.jqm.api.domain.dto.SysRoleMenuDTO;

import java.util.List;

/**
 * 用户菜单业务逻辑实现
 *
 * @author xujianqiang
 * @date 2024/09/27
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRoleMenu(List<SysRoleMenuDTO> roleMenuDTOList) {
        List<SysRoleMenu> roleMenuList = roleMenuDTOList.stream()
                .map(roleMenuDTO -> {
                    SysRoleMenu roleMenu = new SysRoleMenu();
                    BeanUtils.copyProperties(roleMenuDTO, roleMenu);
                    return roleMenu;
                })
                .toList();
        saveBatch(roleMenuList);

        // TODO 清除缓存权限
    }
}
