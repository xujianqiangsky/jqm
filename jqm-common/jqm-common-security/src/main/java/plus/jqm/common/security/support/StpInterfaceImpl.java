package plus.jqm.common.security.support;

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
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import plus.jqm.api.domain.dto.SysRolePermissionDTO;
import plus.jqm.api.service.SysUserRemoteService;
import plus.jqm.common.core.constant.cache.CacheConstants;
import plus.jqm.common.core.domain.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 *
 * @author xujianqiang
 * @date 2024/09/09
 */
public class StpInterfaceImpl implements StpInterface {
    @DubboReference
    private SysUserRemoteService sysUserRemoteService;

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permissionList = new ArrayList<>();
        for (String roleId : getRoleList(loginId, loginType)) {
            List<String> rolePermissionList = (List<String>) SaManager.getSaTokenDao()
                    .getObject(CacheConstants.PERMISSION_CACHE_KEY_PREFIX.getKey() + roleId);
            if (rolePermissionList != null) {
                permissionList.addAll(rolePermissionList);
            }
        }
        return permissionList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roleList = (List<String>) SaManager.getSaTokenDao()
                .getObject(CacheConstants.ROLE_CACHE_KEY_PREFIX.getKey() + loginId);
        if (roleList == null) {
            // 从数据库中获取用户权限信息
            Long id = Long.valueOf(String.valueOf(loginId));
            Result<List<SysRolePermissionDTO>> result = sysUserRemoteService.getUserRoleAndPermissionById(id);
            List<SysRolePermissionDTO> rolePermissionDTOList = result.getData();
            roleList = new ArrayList<>(rolePermissionDTOList.size());
            for (SysRolePermissionDTO rolePermissionDTO : rolePermissionDTOList) {
                roleList.add(rolePermissionDTO.getName());
            }
            // 放入缓存
            SaManager.getSaTokenDao()
                    .setObject(CacheConstants.ROLE_CACHE_KEY_PREFIX.getKey() + loginId, roleList, StpUtil.getTokenTimeout());
            for (SysRolePermissionDTO sysRolePermissionDTO : rolePermissionDTOList) {
                SaManager.getSaTokenDao()
                        .setObject(CacheConstants.PERMISSION_CACHE_KEY_PREFIX.getKey() + sysRolePermissionDTO.getName(), sysRolePermissionDTO.getPermissionList(), StpUtil.getTokenTimeout());
            }
        }
        return roleList;
    }
}
