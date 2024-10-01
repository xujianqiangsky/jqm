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
import plus.jqm.admin.service.SysDeptService;
import plus.jqm.api.domain.dto.SysDeptDTO;
import plus.jqm.api.domain.vo.SysDeptVO;
import plus.jqm.common.core.domain.Result;

/**
 * 部门管理模块
 *
 * @author xujianqiang
 * @date 2024/09/24
 */
@Tag(name = "部门管理模块")
@RestController
@RequestMapping(value = "/dept", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysDeptController {
    private final SysDeptService deptService;

    public SysDeptController(SysDeptService deptService) {this.deptService = deptService;}

    @Operation(summary = "分页查询部门信息")
    @SaCheckPermission("sys:dept:view")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result<IPage<SysDeptVO>> listDepartments(@Parameter(name = "pageNum", description = "当前页码") @PathVariable("pageNum") long pageNum,
                                  @Parameter(name = "pageSize", description = "分页显示条数") @PathVariable(value = "pageSize") long pageSize) {
        IPage<SysDeptVO> deptVOList = deptService.listDepartments(pageNum, pageSize);
        return Result.success(deptVOList);
    }

    @Operation(summary = "根据 id 获取部门信息")
    @SaCheckPermission("sys:dept:view")
    @GetMapping("/{id}")
    public Result<SysDeptVO> getDeptById(@Parameter(name = "id", description = "部门 id") @PathVariable("id") Long id) {
        SysDeptVO deptVO = deptService.getDeptById(id);
        return Result.success(deptVO);
    }

    @Operation(summary = "保存部门信息")
    @SaCheckPermission("sys:dept:add")
    @PostMapping
    public Result<String> saveDept(@RequestBody SysDeptDTO deptDTO) {
        deptService.saveDept(deptDTO);
        return Result.success("ok");
    }

    @Operation(summary = "根据 id 修改部门信息")
    @SaCheckPermission("sys:dept:edit")
    @PutMapping
    public Result<String> updateDeptById(@RequestBody SysDeptDTO deptDTO) {
        deptService.updateDeptById(deptDTO);
        return Result.success("ok");
    }

    @Operation(summary = "根据 id 删除部门信息")
    @SaCheckPermission("sys:dept:remove")
    @DeleteMapping("/{id}")
    public Result<String> removeDeptById(@Parameter(name = "id", description = "部门 id") @PathVariable("id") Long id) {
        deptService.removeById(id);
        return Result.success("ok");
    }
}
