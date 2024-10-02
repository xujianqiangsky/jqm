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

/**
 * 角色菜单管理模块
 *
 * @author xujianqiang
 * @date 2024/10/01
 */

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import plus.jqm.admin.service.SysRoleMenuService;
import plus.jqm.api.domain.dto.SysRoleMenuDTO;
import plus.jqm.api.domain.vo.SysRoleMenuVO;
import plus.jqm.common.core.domain.Result;

import java.util.List;

@Tag(name = "角色菜单管理模块")
@RestController
@RequestMapping(value = "/role/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysRoleMenuController {
    private final SysRoleMenuService roleMenuService;

    public SysRoleMenuController(SysRoleMenuService roleMenuService) {this.roleMenuService = roleMenuService;}

    @Operation(summary = "分页查询角色菜单信息")
    @SaCheckPermission("sys:role:menu:view")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result<IPage<SysRoleMenuVO>> listRelations(@Parameter(name = "pageNum", description = "当前页码") @PathVariable("pageNum") long pageNum,
                                                      @Parameter(name = "pageSize", description = "分页显示条数") @PathVariable(value = "pageSize") long pageSize) {
        IPage<SysRoleMenuVO> page = roleMenuService.listRelations(pageNum, pageSize);
        return Result.success(page);
    }

    @Operation(summary = "保存角色菜单信息")
    @SaCheckPermission("sys:role:menu:add")
    @PostMapping
    public Result<String> saveRoleMenu(@RequestBody List<SysRoleMenuDTO> roleMenuDTOList) {
        roleMenuService.saveRoleMenu(roleMenuDTOList);
        return Result.success("ok");
    }

    @Operation(summary = "删除角色菜单信息")
    @SaCheckPermission("sys:role:menu:remove")
    @DeleteMapping("/{id}")
    public Result<String> deleteRoleMenu(@Parameter(name = "id", description = "角色菜单 id") @PathVariable("id") Long id) {
        roleMenuService.removeById(id);
        return Result.success("ok");
    }
}
