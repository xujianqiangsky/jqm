package plus.jqm.api.domain;

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

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 菜单数据对象
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
@Schema(description = "菜单数据对象")
public class SysMenu implements Serializable {
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
    @Schema(name = "createdBy", description = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    @Schema(name = "createdTime", description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    @Schema(name = "updatedBy", description = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;
    @Schema(name = "updatedTime", description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
    @Schema(name = "deleted", description = "逻辑删除：0 未删除；1 已删除", defaultValue = "0")
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private boolean deleted;


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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysMenu sysMenu)) return false;
        return hidden == sysMenu.hidden && deleted == sysMenu.deleted && Objects.equals(id, sysMenu.id) && Objects.equals(name, sysMenu.name) && Objects.equals(enName, sysMenu.enName) && Objects.equals(parentId, sysMenu.parentId) && Objects.equals(permission, sysMenu.permission) && Objects.equals(path, sysMenu.path) && Objects.equals(icon, sysMenu.icon) && Objects.equals(sortOrder, sysMenu.sortOrder) && Objects.equals(type, sysMenu.type) && Objects.equals(createdBy, sysMenu.createdBy) && Objects.equals(createdTime, sysMenu.createdTime) && Objects.equals(updatedBy, sysMenu.updatedBy) && Objects.equals(updatedTime, sysMenu.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, enName, parentId, permission, path, icon, sortOrder, type, hidden, createdBy, createdTime, updatedBy, updatedTime, deleted);
    }

    @Override
    public String toString() {
        return "SysMenu{" +
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
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", deleted=" + deleted +
                '}';
    }
}
