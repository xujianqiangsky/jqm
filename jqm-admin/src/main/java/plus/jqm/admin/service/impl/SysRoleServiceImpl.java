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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import plus.jqm.admin.exception.RoleNameAlreadyExistsException;
import plus.jqm.admin.mapper.SysRoleMapper;
import plus.jqm.admin.service.SysRoleService;
import plus.jqm.api.domain.SysRole;
import plus.jqm.api.domain.dto.SysRoleDTO;
import plus.jqm.api.domain.vo.SysRoleVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色业务逻辑接口实现
 *
 * @author xujianqiang
 * @date 2024/09/24
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public IPage<SysRoleVO> listRoles(long pageNum, long pageSize) {
        IPage<SysRole> rolePage = new Page<>(pageNum, pageSize);
        page(rolePage);
        IPage<SysRoleVO> roleVOPage = new Page<>();
        List<SysRoleVO> roleVOList = new ArrayList<>();
        BeanUtils.copyProperties(rolePage, roleVOPage, "records");
        for (SysRole role : rolePage.getRecords()) {
            SysRoleVO roleVO = new SysRoleVO();
            BeanUtils.copyProperties(role, roleVO);
            roleVOList.add(roleVO);
        }
        roleVOPage.setRecords(roleVOList);
        return roleVOPage;
    }

    @Override
    public SysRoleVO getRoleById(Long id) {
        SysRoleVO roleVO = new SysRoleVO();
        SysRole role = getById(id);
        if (role != null) {
            BeanUtils.copyProperties(role, roleVO);
        }
        return roleVO;
    }

    @Override
    public void saveRole(SysRoleDTO roleDTO) {
        checkRoleName(roleDTO.getName());
        SysRole role = new SysRole();
        roleDTO.setId(null);
        BeanUtils.copyProperties(roleDTO, role);
        save(role);
    }

    @Override
    public void updateRoleById(SysRoleDTO roleDTO) {
        SysRole role = getById(roleDTO.getId());
        if (role != null) {
            BeanUtils.copyProperties(roleDTO, role);
            updateById(role);
        }
    }

    public void checkRoleName(String roleName) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRole::getName, roleName);
        SysRole role = getOne(queryWrapper);
        if (role != null) {
            throw new RoleNameAlreadyExistsException();
        }
    }
}
