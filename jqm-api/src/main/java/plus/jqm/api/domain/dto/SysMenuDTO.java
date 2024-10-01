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
 * 菜单数据传输对象
 *
 * @author xujianqiang
 * @date 2024/09/24
 */
@Schema(description = "菜单数据传输对象")
public class SysMenuDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "菜单 id")
    private Long id;
    @Schema(name = "name", description = "菜单名称")
    private String name;
    @Schema(name = "enName", description = "菜单英文名称")
    private String enName;
    @Schema(name = "parentId", description = "菜单所属父级 id")
    private Long parentId;
    @Schema(name = "permission", description = "权限")
    private String permission;
    @Schema(name = "path", description = "路径")
    private String path;
    @Schema(name = "icon", description = "菜单图标")
    private String icon;
    @Schema(name = "sortOrder", description = "排序", defaultValue = "0")
    private Integer sortOrder;
    @Schema(name = "type", description = "菜单类型：0 目录；1 菜单；2 按钮", defaultValue = "0")
    private Integer type;
    @Schema(name = "hidden", description = "菜单是否隐藏：0 未隐藏；1 已隐藏", defaultValue = "0")
    private boolean hidden;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysMenuDTO that)) return false;
        return hidden == that.hidden && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(enName, that.enName) && Objects.equals(parentId, that.parentId) && Objects.equals(permission, that.permission) && Objects.equals(path, that.path) && Objects.equals(icon, that.icon) && Objects.equals(sortOrder, that.sortOrder) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, enName, parentId, permission, path, icon, sortOrder, type, hidden);
    }

    @Override
    public String toString() {
        return "SysMenuDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enName='" + enName + '\'' +
                ", parentId=" + parentId +
                ", permission='" + permission + '\'' +
                ", path='" + path + '\'' +
                ", icon='" + icon + '\'' +
                ", sortOrder=" + sortOrder +
                ", type=" + type +
                ", hidden=" + hidden +
                '}';
    }
}
