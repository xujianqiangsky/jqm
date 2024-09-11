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
import plus.jqm.admin.mapper.SysUserMapper;
import plus.jqm.admin.service.SysUserService;
import plus.jqm.api.domain.SysMenu;
import plus.jqm.api.domain.SysRole;
import plus.jqm.api.domain.SysUser;
import plus.jqm.api.domain.SysUserDetail;
import plus.jqm.api.domain.vo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务逻辑实现
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private final SysUserMapper userMapper;

    public SysUserServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public SysUserVO getUserById(Long id) {
        SysUserVO userVO = new SysUserVO();
        SysUser user = baseMapper.selectById(id);
        if (user != null) {
            BeanUtils.copyProperties(user, userVO);
        }
        return userVO;
    }

    @Override
    public SysUserDetailVO getUserDetailById(Long id) {
        SysUserDetailVO userDetailVO = new SysUserDetailVO();
        SysDeptVO deptVO = new SysDeptVO();
        SysUserDetail userDetail = userMapper.getUserDetailById(id);
        if (userDetail != null) {
            // 数据拷贝
            BeanUtils.copyProperties(userDetail, userDetailVO);
            BeanUtils.copyProperties(userDetail.getDept(), deptVO);
            List<SysRole> roleList = userDetail.getRoleList();
            List<SysMenu> menuList = userDetail.getMenuList();
            ArrayList<SysRoleVO> roleVOList = new ArrayList<>(roleList.size());
            ArrayList<SysMenuVO> menuVOList = new ArrayList<>(menuList.size());
            for (SysRole role : roleList) {
                SysRoleVO roleVO = new SysRoleVO();
                BeanUtils.copyProperties(role, roleVO);
                roleVOList.add(roleVO);
            }
            for (SysMenu menu : menuList) {
                SysMenuVO menuVO = new SysMenuVO();
                BeanUtils.copyProperties(menu, menuVO);
                menuVOList.add(menuVO);
            }
            userDetailVO.setDept(deptVO);
            userDetailVO.setRoleList(roleVOList);
            userDetailVO.setMenuList(menuVOList);
        }
        return userDetailVO;
    }
}
