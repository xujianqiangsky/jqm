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
import plus.jqm.admin.exception.DeptNameAlreadyExistsException;
import plus.jqm.admin.mapper.SysDeptMapper;
import plus.jqm.admin.service.SysDeptService;
import plus.jqm.api.domain.SysDept;
import plus.jqm.api.domain.dto.SysDeptDTO;
import plus.jqm.api.domain.vo.SysDeptVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门业务逻辑实现
 *
 * @author xujianqiang
 * @date 2024/09/24
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Override
    public IPage<SysDeptVO> listDepartments(long pageNum, long pageSize) {
        Page<SysDept> deptPage = new Page<>(pageNum, pageSize);
        page(deptPage);
        Page<SysDeptVO> deptVOPage = new Page<>();
        List<SysDeptVO> deptVOList = new ArrayList<>();
        BeanUtils.copyProperties(deptPage, deptVOPage, "records");
        for (SysDept sysDept : deptPage.getRecords()) {
            SysDeptVO sysDeptVO = new SysDeptVO();
            BeanUtils.copyProperties(sysDept, sysDeptVO);
            deptVOList.add(sysDeptVO);
        }
        deptVOPage.setRecords(deptVOList);
        return deptVOPage;
    }

    @Override
    public SysDeptVO getDeptById(Long id) {
        SysDept dept = getById(id);
        SysDeptVO deptVO = new SysDeptVO();
        if (dept != null) {
            BeanUtils.copyProperties(dept, deptVO);
        }
        return deptVO;
    }

    @Override
    public void saveDept(SysDeptDTO deptDTO) {
        checkDeptName(deptDTO.getName());
        SysDept sysDept = new SysDept();
        deptDTO.setId(null);
        BeanUtils.copyProperties(deptDTO, sysDept);
        save(sysDept);
    }

    @Override
    public void updateDeptById(SysDeptDTO deptDTO) {
        SysDept dept = getById(deptDTO.getId());
        if (dept != null) {
            BeanUtils.copyProperties(deptDTO, dept);
            updateById(dept);
        }
    }

    public void checkDeptName(String deptName) {
        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDept::getName, deptName);
        SysDept dept = getOne(queryWrapper);
        if (dept != null) {
            throw new DeptNameAlreadyExistsException();
        }
    }
}
