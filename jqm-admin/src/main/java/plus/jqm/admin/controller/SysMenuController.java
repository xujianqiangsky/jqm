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
import plus.jqm.admin.service.SysMenuService;
import plus.jqm.api.domain.dto.SysMenuDTO;
import plus.jqm.api.domain.vo.SysMenuDetailVO;
import plus.jqm.api.domain.vo.SysMenuVO;
import plus.jqm.common.core.domain.Result;

import java.util.List;

/**
 * 菜单管理模块
 *
 * @author xujianqiang
 * @date 2024/09/24
 */
@Tag(name = "菜单管理模块")
@RestController
@RequestMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysMenuController {
    private final SysMenuService menuService;

    public SysMenuController(SysMenuService menuService) {
        this.menuService = menuService;
    }

    @Operation(summary = "分页查询菜单信息")
    @SaCheckPermission("sys:menu:view")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result<IPage<SysMenuVO>> listMenus(@Parameter(name = "pageNum", description = "当前页码") @PathVariable("pageNum") long pageNum,
                                              @Parameter(name = "pageSize", description = "分页显示条数") @PathVariable(value = "pageSize") long pageSize) {
        IPage<SysMenuVO> page = menuService.listMenus(pageNum, pageSize);
        return Result.success(page);
    }

    @Operation(summary = "根据 Id 获取菜单信息")
    @SaCheckPermission("sys:menu:view")
    @GetMapping("/{id}")
    public Result<SysMenuVO> getMenuById(@Parameter(name = "id", description = "菜单 id") @PathVariable("id") Long id) {
        SysMenuVO menuVO = menuService.getMenuById(id);
        return Result.success(menuVO);
    }

    @Operation(summary = "获取登录用户菜单信息")
    @GetMapping("/detail")
    public Result<List<SysMenuDetailVO>> getMenuByUserId() {
        List<SysMenuDetailVO> userMenuVOList = menuService.getMenuByUserId(StpUtil.getLoginIdAsLong());
        return Result.success(userMenuVOList);
    }

    @Operation(summary = "保存菜单信息")
    @SaCheckPermission("sys:menu:add")
    @PostMapping
    public Result<String> saveMenu(@RequestBody SysMenuDTO menuDTO) {
        menuService.saveMenu(menuDTO);
        return Result.success("ok");
    }

    @Operation(summary = "根据 id 修改指定菜单信息")
    @SaCheckPermission("sys:menu:edit")
    @PutMapping
    public Result<String> updateMenuById(@RequestBody SysMenuDTO menuDTO) {
        menuService.updateMenuById(menuDTO);
        return Result.success("ok");
    }

    @Operation(summary = "根据 id 删除指定菜单信息")
    @SaCheckPermission("sys:menu:remove")
    @DeleteMapping("/{id}")
    public Result<String> removeMenuById(@Parameter(name = "id", description = "菜单 id") @PathVariable("id") Long id) {
        menuService.removeById(id);
        return Result.success("ok");
    }
}
