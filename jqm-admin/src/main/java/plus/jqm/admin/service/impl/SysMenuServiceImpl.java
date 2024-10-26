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

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import plus.jqm.admin.exception.MenuNameAlreadyExistsException;
import plus.jqm.admin.mapper.SysMenuMapper;
import plus.jqm.admin.service.SysMenuService;
import plus.jqm.api.domain.SysMenu;
import plus.jqm.api.domain.dto.SysMenuDTO;
import plus.jqm.api.domain.vo.SysMenuDetailVO;
import plus.jqm.api.domain.vo.SysMenuVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 菜单业务逻辑实现
 *
 * @author xujianqiang
 * @date 2024/09/24
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Override
    public IPage<SysMenuVO> listMenus(long pageNum, long pageSize) {
        IPage<SysMenu> menuPage = new Page<>(pageNum, pageSize);
        page(menuPage);
        IPage<SysMenuVO> menuVOPage = new Page<>();
        BeanUtils.copyProperties(menuPage, menuVOPage, "records");
        List<SysMenuVO> menuVOList = new ArrayList<>();
        for (SysMenu menu : menuPage.getRecords()) {
            SysMenuVO sysMenuVO = new SysMenuVO();
            BeanUtils.copyProperties(menu, sysMenuVO);
            menuVOList.add(sysMenuVO);
        }
        menuVOPage.setRecords(menuVOList);
        return menuVOPage;
    }

    @Cacheable("menu")
    @Override
    public List<SysMenuDetailVO> getMenuByUserId(long userId) {
        List<SysMenu> menuList;
        if (StpUtil.hasPermission("*")) {
            LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.ne(SysMenu::getName, "*");
            menuList = list(queryWrapper);
        } else {
            menuList = baseMapper.selectMenuByUserId(userId);
        }
        List<SysMenuDetailVO> parentList = menuList.stream()
                .filter(menu -> menu.getParentId() == null)
                .map(menu -> {
                    SysMenuDetailVO menuDetailVO = new SysMenuDetailVO();
                    BeanUtils.copyProperties(menu, menuDetailVO);
                    return menuDetailVO;
                })
                .sorted(Comparator.comparingInt(SysMenuDetailVO::getSortOrder))
                .toList();
        List<SysMenuDetailVO> children = menuList.stream()
                .filter(menu -> menu.getParentId() != null)
                .map(menu -> {
                    SysMenuDetailVO menuDetailVO = new SysMenuDetailVO();
                    BeanUtils.copyProperties(menu, menuDetailVO);
                    return menuDetailVO;
                })
                .toList();
        handleMenuDetail(parentList, children);
        return parentList;
    }

    @Override
    public SysMenuVO getMenuById(Long id) {
        SysMenu menu = getById(id);
        SysMenuVO menuVO = new SysMenuVO();
        if (menu != null) {
            BeanUtils.copyProperties(menu, menuVO);
        }
        return menuVO;
    }

    @Override
    public void saveMenu(SysMenuDTO menuDTO) {
        checkMenuName(menuDTO.getName());
        menuDTO.setId(null);
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuDTO, menu);
        save(menu);
    }

    @Override
    public void updateMenuById(SysMenuDTO menuDTO) {
        SysMenu menu = getById(menuDTO.getId());
        if (menu != null) {
            BeanUtils.copyProperties(menuDTO, menu);
            updateById(menu);
        }
    }

    public void checkMenuName(String menuName) {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysMenu::getName, menuName);
        SysMenu menu = getOne(queryWrapper);
        if (menu != null) {
            throw new MenuNameAlreadyExistsException();
        }
    }

    private void handleMenuDetail(List<SysMenuDetailVO> parentList, List<SysMenuDetailVO> sourceList) {
        for (SysMenuDetailVO parent : parentList) {
            List<SysMenuDetailVO> children = sourceList.stream()
                    .filter(child -> parent.getId().equals(child.getParentId()))
                    .sorted(Comparator.comparingInt(SysMenuDetailVO::getSortOrder))
                    .toList();
            handleMenuDetail(children, sourceList);
            parent.setChildren(children);
        }
    }
}
