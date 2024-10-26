package plus.jqm.admin.controller;

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

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import plus.jqm.admin.service.SysUserService;
import plus.jqm.api.domain.dto.SysUserDTO;
import plus.jqm.api.domain.vo.SysUserDetailVO;
import plus.jqm.api.domain.vo.SysUserVO;
import plus.jqm.common.core.domain.Result;

/**
 * 用户管理模块
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
@Tag(name = "用户管理模块")
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysUserController {
    private final SysUserService userService;

    public SysUserController(SysUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "分页查询用户信息")
    @SaCheckPermission("sys:user:view")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result<IPage<SysUserVO>> listUsers(@Parameter(name = "pageNum", description = "当前页码") @PathVariable("pageNum") long pageNum,
                                                    @Parameter(name = "pageSize", description = "分页显示条数") @PathVariable(value = "pageSize") long pageSize) {
        IPage<SysUserVO> page = userService.listUsers(pageNum, pageSize);
        return Result.success(page);
    }

    @Operation(summary = "分页查询用户详细信息")
    @SaCheckPermission("sys:user:view")
    @GetMapping("/list/detail/{pageNum}/{pageSize}")
    public Result<IPage<SysUserDetailVO>> listUserDetails(@Parameter(name = "pageNum", description = "当前页码") @PathVariable("pageNum") long pageNum,
                                              @Parameter(name = "pageSize", description = "分页显示条数") @PathVariable(value = "pageSize") long pageSize) {
        IPage<SysUserDetailVO> page = userService.listUserDetails(pageNum, pageSize);
        return Result.success(page);
    }

    @Operation(summary = "获取登录用户信息")
    @GetMapping
    public Result<SysUserVO> getLoginUserInfo() {
        SysUserVO userVO = userService.getUserById(StpUtil.getLoginIdAsLong());
        return Result.success(userVO);
    }

    @Operation(summary = "获取登录用户详细信息")
    @GetMapping("/detail")
    public Result<SysUserDetailVO> getLoginUserDetailInfo() {
        SysUserDetailVO userDetailVO = userService.getUserDetailById(StpUtil.getLoginIdAsLong());
        return Result.success(userDetailVO);
    }

    @Operation(summary = "保存用户信息")
    @SaCheckPermission("sys:user:add")
    @PostMapping
    public Result<String> saveUser(@RequestBody SysUserDTO userDTO) {
        userService.saveUser(userDTO);
        return Result.success("ok");
    }

    @Operation(summary = "修改登录用户信息")
    @PutMapping
    public Result<String> updateLoginUser(@RequestBody SysUserDTO userDTO) {
        userDTO.setId(StpUtil.getLoginIdAsLong());
        userService.updateUserById(userDTO);
        return Result.success("ok");
    }

    @Operation(summary = "修改登录用户密码")
    @PutMapping("/passwd")
    public Result<String> updateLoginPassword(@RequestBody SysUserDTO userDTO) {
        userService.updateLoginPassword(userDTO);
        return Result.success("ok");
    }

    @Operation(summary = "修改登录用户手机号码")
    @PutMapping("/mobile")
    public Result<String> updateLoginUserMobileNumber(@RequestBody SysUserDTO userDTO) {
        userService.updateLoginUserMobileNumber(userDTO);
        return Result.success("ok");
    }

    @Operation(summary = "根据 id 获取指定用户信息")
    @SaCheckPermission("sys:user:view")
    @GetMapping("/{id}")
    public Result<SysUserVO> getUserInfoById(@Parameter(name = "id", description = "用户 id") @PathVariable("id") Long id) {
        SysUserVO userVO = userService.getUserById(id);
        return Result.success(userVO);
    }

    @Operation(summary = "根据 id 修改指定用户信息")
    @SaCheckPermission("sys:user:edit")
    @PutMapping("/change")
    public Result<String> updateUserById(@RequestBody SysUserDTO userDTO) {
        userService.updateUserById(userDTO);
        return Result.success("ok");
    }
}
