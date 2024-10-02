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
import plus.jqm.admin.service.SysUserRoleService;
import plus.jqm.api.domain.dto.SysUserRoleDTO;
import plus.jqm.api.domain.vo.SysUserRoleVO;
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

    @Operation(summary = "分页查询用户角色信息")
    @SaCheckPermission("sys:user:role:view")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result<IPage<SysUserRoleVO>> listRelations(@Parameter(name = "pageNum", description = "当前页码") @PathVariable("pageNum") long pageNum,
                                                      @Parameter(name = "pageSize", description = "分页显示条数") @PathVariable(value = "pageSize") long pageSize) {
        IPage<SysUserRoleVO> page = userRoleService.listRelations(pageNum, pageSize);
        return Result.success(page);
    }

    @Operation(summary = "保存用户角色信息")
    @SaCheckPermission("sys:user:role:add")
    @PostMapping
    public Result<String> saveUserRole(@RequestBody List<SysUserRoleDTO> userRoleDTOList) {
        userRoleService.saveUserRole(userRoleDTOList);
        return Result.success("ok");
    }

    @Operation(summary = "删除用户角色信息")
    @SaCheckPermission("sys:user:role:remove")
    @DeleteMapping("/{id}")
    public Result<String> deleteUserRole(@Parameter(name = "id", description = "用户角色 id") @PathVariable("id") Long id) {
        userRoleService.removeById(id);
        return Result.success("ok");
    }
}
