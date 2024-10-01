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
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import plus.jqm.admin.service.SysRoleService;
import plus.jqm.api.domain.dto.SysRoleDTO;
import plus.jqm.api.domain.vo.SysRoleVO;
import plus.jqm.common.core.domain.Result;

/**
 * 角色管理模块
 *
 * @author xujianqiang
 * @date 2024/09/24
 */
@Tag(name = "角色管理模块")
@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysRoleController {
    private final SysRoleService roleService;

    public SysRoleController(SysRoleService roleService) {this.roleService = roleService;}

    @Operation(summary = "分页查询角色信息")
    @SaCheckPermission("sys:role:view")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result<IPage<SysRoleVO>> listRoles(@Parameter(name = "pageNum", description = "当前页码") @PathVariable("pageNum") long pageNum,
                                              @Parameter(name = "pageSize", description = "分页显示条数") @PathVariable(value = "pageSize") long pageSize) {
        IPage<SysRoleVO> page = roleService.listRoles(pageNum, pageSize);
        return Result.success(page);
    }

    @Operation(summary = "根据 id 获取角色信息")
    @SaCheckPermission("sys:role:view")
    @GetMapping("/{id}")
    public Result<SysRoleVO> getRoleById(@Parameter(name = "id", description = "角色 id") @PathVariable("id") Long id) {
        SysRoleVO roleVO = roleService.getRoleById(id);
        return Result.success(roleVO);
    }

    @Operation(summary = "保存角色信息")
    @SaCheckPermission("sys:role:add")
    @PostMapping
    public Result<String> saveRole(@RequestBody SysRoleDTO roleDTO) {
        roleService.saveRole(roleDTO);
        return Result.success("ok");
    }

    @Operation(summary = "根据 id 修改角色信息")
    @SaCheckPermission("sys:role:edit")
    @PutMapping
    public Result<String> updateRoleById(@RequestBody SysRoleDTO roleDTO) {
        roleService.updateRoleById(roleDTO);
        return Result.success("ok");
    }

    @Operation(summary = "根据 id 删除角色信息")
    @SaCheckPermission("sys:role:remove")
    @DeleteMapping("/{id}")
    public Result<String> removeRoleById(@Parameter(name = "id", description = "角色 id") @PathVariable("id") Long id) {
        roleService.removeById(id);
        return Result.success("ok");
    }
}
