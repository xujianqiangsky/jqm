package plus.jqm.api.domain.vo;

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

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色菜单展示对象
 *
 * @author xujianqiang
 * @date 2024/09/27
 */
@Schema(description = "角色菜单展示对象")
public class SysRoleMenuVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "roleId", description = "角色 id")
    private Long roleId;
    @Schema(name = "name", description = "角色名称")
    private String roleName;
    @Schema(name = "menuId", description = "菜单 id")
    private Long menuId;
    @Schema(name = "name", description = "菜单名称")
    private String menuName;
    @Schema(name = "createdBy", description = "创建人")
    private String createdBy;
    @Schema(name = "createdTime", description = "创建时间")
    private LocalDateTime createdTime;
    @Schema(name = "updatedBy", description = "更新人")
    private String updatedBy;
    @Schema(name = "updatedTime", description = "更新时间")
    private LocalDateTime updatedTime;
}
