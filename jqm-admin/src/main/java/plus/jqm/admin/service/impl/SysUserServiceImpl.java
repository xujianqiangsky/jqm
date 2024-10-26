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

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import plus.jqm.admin.constant.CheckCondition;
import plus.jqm.admin.exception.MobileNumberAlreadyExistsException;
import plus.jqm.admin.exception.UsernameAlreadyExistsException;
import plus.jqm.admin.mapper.SysUserMapper;
import plus.jqm.admin.service.SysUserService;
import plus.jqm.api.domain.*;
import plus.jqm.api.domain.dto.SysUserDTO;
import plus.jqm.api.domain.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户业务逻辑实现
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public IPage<SysUserVO> listUsers(long pageNum, long pageSize) {
        IPage<SysUser> userPage = new Page<>(pageNum, pageSize);
        page(userPage);
        IPage<SysUserVO> userVOPage = new Page<>();
        BeanUtils.copyProperties(userPage, userVOPage, "records");
        List<SysUserVO> userVOList = new ArrayList<>();
        for (SysUser user : userPage.getRecords()) {
            SysUserVO userVO = new SysUserVO();
            BeanUtils.copyProperties(user, userVO);
            userVOList.add(userVO);
        }
        userVOPage.setRecords(userVOList);
        return userVOPage;
    }

    @Override
    public IPage<SysUserDetailVO> listUserDetails(long pageNum, long pageSize) {
        IPage<SysUserDetail> userDetailPage = new Page<>(pageNum, pageSize);
        baseMapper.pageUserDetails(userDetailPage);
        IPage<SysUserDetailVO> userDetailVOPage = new Page<>();
        BeanUtils.copyProperties(userDetailPage, userDetailVOPage, "records");
        List<SysUserDetailVO> userDetailVOList = new ArrayList<>();
        for (SysUserDetail userDetail : userDetailPage.getRecords()) {
            SysUserDetailVO userDetailVO = new SysUserDetailVO();
            SysDeptVO deptVO = new SysDeptVO();
            // 数据拷贝
            userDetailToUserDetailVO(userDetail, userDetailVO, deptVO);
            userDetailVOList.add(userDetailVO);
        }
        userDetailVOPage.setRecords(userDetailVOList);
        return userDetailVOPage;
    }

    @Override
    public SysUserVO getUserById(Long id) {
        SysUserVO userVO = new SysUserVO();
        SysUser user = getById(id);
        if (user != null) {
            BeanUtils.copyProperties(user, userVO);
        }
        return userVO;
    }

    @Override
    public SysUserDetailVO getUserDetailById(Long id) {
        SysUserDetailVO userDetailVO = new SysUserDetailVO();
        SysDeptVO deptVO = new SysDeptVO();
        SysUserDetail userDetail = baseMapper.selectUserDetailById(id);
        if (userDetail != null) {
            // 数据拷贝
            userDetailToUserDetailVO(userDetail, userDetailVO, deptVO);
        }
        return userDetailVO;
    }

    @Override
    public void saveUser(SysUserDTO userDTO) {
        checkUsernameAndMobileNumber(userDTO, CheckCondition.ALL);
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDTO, user);
        String salt = BCrypt.gensalt();
        String password = BCrypt.hashpw(user.getPassword(), salt);
        user.setId(null);
        user.setPassword(password);
        user.setSalt(salt);
        save(user);
    }

    @Override
    public void updateUserById(SysUserDTO userDTO) {
        SysUser user = getById(userDTO.getId());
        if (user != null) {
            user.setName(userDTO.getName());
            user.setDeptId(userDTO.getDeptId());
            user.setGender(userDTO.getGender());
            user.setAvatar(userDTO.getAvatar());
            user.setEmail(userDTO.getEmail());
            updateById(user);
        }
    }

    @Override
    public void updateLoginPassword(SysUserDTO userDTO) {
        SysUser user = new SysUser();
        String salt = BCrypt.gensalt();
        String password = BCrypt.hashpw(userDTO.getPassword(), salt);
        user.setId(StpUtil.getLoginIdAsLong());
        user.setPassword(password);
        user.setSalt(salt);
        updateById(user);
    }

    @Override
    public void updateLoginUserMobileNumber(SysUserDTO userDTO) {
        checkUsernameAndMobileNumber(userDTO, CheckCondition.MOBILE_NUMBER);
        SysUser user = new SysUser();
        user.setId(StpUtil.getLoginIdAsLong());
        user.setMobileNumber(userDTO.getMobileNumber());
        updateById(user);
    }

    public void checkUsernameAndMobileNumber(SysUserDTO userDTO, CheckCondition condition) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, userDTO.getUsername())
                .or()
                .eq(SysUser::getMobileNumber, userDTO.getMobileNumber());
        List<SysUser> userList = list(queryWrapper);
        for (SysUser user : userList) {
            if ((condition.equals(CheckCondition.ALL) || condition.equals(CheckCondition.USERNAME)) && Objects.equals(user.getUsername(), userDTO.getUsername())) {
                throw new UsernameAlreadyExistsException();
            }
            if ((condition.equals(CheckCondition.ALL) || condition.equals(CheckCondition.MOBILE_NUMBER)) && Objects.equals(user.getMobileNumber(), userDTO.getMobileNumber())) {
                throw new MobileNumberAlreadyExistsException();
            }
        }
    }

    private void userDetailToUserDetailVO(SysUserDetail userDetail, SysUserDetailVO userDetailVO, SysDeptVO deptVO) {
        BeanUtils.copyProperties(userDetail, userDetailVO);
        SysDept dept = userDetail.getDept();
        if (dept != null) {
            BeanUtils.copyProperties(dept, deptVO);
        }
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
}
