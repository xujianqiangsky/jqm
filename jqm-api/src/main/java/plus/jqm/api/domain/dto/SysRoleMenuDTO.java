package plus.jqm.api.domain.dto;

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
import java.util.Objects;

/**
 * 角色菜单数据传输对象
 *
 * @author xujianqiang
 * @date 2024/09/27
 */
@Schema(description = "角色菜单数据传输对象")
public class SysRoleMenuDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "角色菜单 id")
    private Long id;
    @Schema(name = "roleId", description = "角色 id")
    private Long roleId;
    @Schema(name = "roleName", description = "角色名称")
    private String roleName;
    @Schema(name = "menuId", description = "菜单 id")
    private Long menuId;
    @Schema(name = "permission", description = "权限")
    private String permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysRoleMenuDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(roleId, that.roleId) && Objects.equals(roleName, that.roleName) && Objects.equals(menuId, that.menuId) && Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, roleName, menuId, permission);
    }

    @Override
    public String toString() {
        return "SysRoleMenuDTO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", menuId=" + menuId +
                ", permission='" + permission + '\'' +
                '}';
    }
}
