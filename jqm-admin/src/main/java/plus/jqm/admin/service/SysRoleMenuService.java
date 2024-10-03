package plus.jqm.admin.service;

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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.jqm.api.domain.SysRoleMenu;
import plus.jqm.api.domain.dto.SysRoleMenuDTO;
import plus.jqm.api.domain.vo.SysRoleMenuVO;

import java.util.List;

/**
 * 角色菜单业务逻辑接口
 *
 * @author xujianqiang
 * @date 2024/09/27
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    void saveRoleMenu(List<SysRoleMenuDTO> roleMenuDTOList);

    IPage<SysRoleMenuVO> listRelations(long pageNum, long pageSize);

    void removeRoleMenuById(Long id);
}
