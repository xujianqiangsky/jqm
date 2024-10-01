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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.jqm.admin.service.SysUserRoleService;
import plus.jqm.api.domain.dto.SysUserRoleDTO;
import plus.jqm.common.core.domain.Result;

import java.util.List;

/**
 * 用户角色管理模块
 *
 * @author xujianqiang
 * @date 2024/09/27
 */
@Tag(name = "用户角色管理模块")
@RestController
@RequestMapping(value = "/user/role/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysUserRoleController {
    private final SysUserRoleService userRoleService;

    public SysUserRoleController(SysUserRoleService userRoleService) {this.userRoleService = userRoleService;}

    @Operation(summary = "保存用户角色信息")
    @SaCheckPermission("sys:user:role:add")
    @PostMapping
    public Result<String> saveUserRole(@RequestBody List<SysUserRoleDTO> userRoleDTOList) {
        userRoleService.saveUserRole(userRoleDTOList);
        return Result.success("ok");
    }
}
