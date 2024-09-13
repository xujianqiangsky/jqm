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
 * 部门数据对象
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
@Schema(description = "用户数据对象")
public class SysDept implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "部门 id")
    private Long id;
    @Schema(name = "name", description = "部门名称")
    private String name;
    @Schema(name = "parentId", description = "部门所属父级 id")
    private Long parentId;
    @Schema(name = "sortOrder", description = "排序", defaultValue = "0")
    private Integer sortOrder;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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
        if (!(o instanceof SysDept sysDept)) return false;
        return deleted == sysDept.deleted && Objects.equals(id, sysDept.id) && Objects.equals(name, sysDept.name) && Objects.equals(parentId, sysDept.parentId) && Objects.equals(sortOrder, sysDept.sortOrder) && Objects.equals(createdBy, sysDept.createdBy) && Objects.equals(createdTime, sysDept.createdTime) && Objects.equals(updatedBy, sysDept.updatedBy) && Objects.equals(updatedTime, sysDept.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, sortOrder, createdBy, createdTime, updatedBy, updatedTime, deleted);
    }

    @Override
    public String toString() {
        return "SysDept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", sortOrder=" + sortOrder +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", deleted=" + deleted +
                '}';
    }
}
