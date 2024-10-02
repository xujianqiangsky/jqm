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
import java.util.Objects;

/**
 * 用户角色展示对象
 *
 * @author xujianqiang
 * @date 2024/09/27
 */
@Schema(description = "用户角色展示对象")
public class SysUserRoleVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "用户角色 id")
    private Long id;
    @Schema(name = "userId", description = "用户 id")
    private Long userId;
    @Schema(name = "username", description = "用户名")
    private String username;
    @Schema(name = "roleId", description = "角色 id")
    private Long roleId;
    @Schema(name = "roleName", description = "角色名称")
    private String roleName;
    @Schema(name = "createdBy", description = "创建人")
    private String createdBy;
    @Schema(name = "createdTime", description = "创建时间")
    private LocalDateTime createdTime;
    @Schema(name = "updatedBy", description = "更新人")
    private String updatedBy;
    @Schema(name = "updatedTime", description = "更新时间")
    private LocalDateTime updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (!(o instanceof SysUserRoleVO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(roleId, that.roleId) && Objects.equals(roleName, that.roleName) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdTime, that.createdTime) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(updatedTime, that.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, username, roleId, roleName, createdBy, createdTime, updatedBy, updatedTime);
    }

    @Override
    public String toString() {
        return "SysUserRoleVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}