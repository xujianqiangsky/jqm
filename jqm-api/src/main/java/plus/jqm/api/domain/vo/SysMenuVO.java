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

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 菜单展示对象
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
public class SysMenuVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String enName;
    private Long parentId;
    private String permission;
    private String path;
    private String icon;
    private Integer sortOrder;
    private Integer type;
    private boolean hidden;
    private String createdBy;
    private LocalDateTime createdTime;
    private String updatedBy;
    private LocalDateTime updatedTime;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysMenuVO sysMenuVO)) return false;
        return hidden == sysMenuVO.hidden && Objects.equals(id, sysMenuVO.id) && Objects.equals(name, sysMenuVO.name) && Objects.equals(enName, sysMenuVO.enName) && Objects.equals(parentId, sysMenuVO.parentId) && Objects.equals(permission, sysMenuVO.permission) && Objects.equals(path, sysMenuVO.path) && Objects.equals(icon, sysMenuVO.icon) && Objects.equals(sortOrder, sysMenuVO.sortOrder) && Objects.equals(type, sysMenuVO.type) && Objects.equals(createdBy, sysMenuVO.createdBy) && Objects.equals(createdTime, sysMenuVO.createdTime) && Objects.equals(updatedBy, sysMenuVO.updatedBy) && Objects.equals(updatedTime, sysMenuVO.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, enName, parentId, permission, path, icon, sortOrder, type, hidden, createdBy, createdTime, updatedBy, updatedTime);
    }

    @Override
    public String toString() {
        return "SysMenuVO{" +
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
                '}';
    }
}
