package plus.jqm.admin.service.impl.remote;

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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import plus.jqm.admin.mapper.SysUserMapper;
import plus.jqm.api.domain.SysRolePermission;
import plus.jqm.api.domain.SysUser;
import plus.jqm.api.domain.dto.SysRolePermissionDTO;
import plus.jqm.api.domain.dto.SysUserDTO;
import plus.jqm.api.service.SysUserRemoteService;
import plus.jqm.common.core.domain.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息远程调用服务实现
 *
 * @author xujianqiang
 * @date 2024/09/08
 */
@DubboService
public class SysUserRemoteServiceImpl implements SysUserRemoteService {
    private final SysUserMapper userMapper;

    public SysUserRemoteServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result<SysUserDTO> getUserByUsername(String username) {
        SysUserDTO userDTO = new SysUserDTO();
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        SysUser user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            BeanUtils.copyProperties(user, userDTO);
        }
        return Result.success(userDTO);
    }

    @Override
    public Result<List<SysRolePermissionDTO>> getUserRoleAndPermissionById(Long id) {
        List<SysRolePermissionDTO> rolePermissionDTOList = new ArrayList<>();
        List<SysRolePermission> rolePermissionList = userMapper.selectRolePermissionByUserId(id);
        if (rolePermissionList != null) {
            for (SysRolePermission rolePermission : rolePermissionList) {
                SysRolePermissionDTO rolePermissionDTO = new SysRolePermissionDTO();
                BeanUtils.copyProperties(rolePermission, rolePermissionDTO);
                rolePermissionDTOList.add(rolePermissionDTO);
            }
        }
        return Result.success(rolePermissionDTOList);
    }
}
