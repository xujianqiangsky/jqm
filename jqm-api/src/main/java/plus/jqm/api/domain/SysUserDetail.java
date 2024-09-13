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

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 用户详细信息数据对象
 *
 * @author xujianqiang
 * @date 2024/09/10
 */
@Schema(description = "用户详细信息数据对象")
public class SysUserDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "用户 id")
    private Long id;
    @Schema(name = "username", description = "用户名")
    private String username;
    @Schema(name = "name", description = "姓名")
    private String name;
    @Schema(name = "password", description = "用户密码")
    private String password;
    @Schema(name = "salt", description = "盐值")
    private String salt;
    @Schema(name = "gender", description = "性别：0 男；1 女；2 未知", defaultValue = "0")
    private Integer gender;
    @Schema(name = "mobileNumber", description = "手机号码")
    private String mobileNumber;
    @Schema(name = "avatar", description = "用户头像")
    private String avatar;
    @Schema(name = "email", description = "用户邮箱")
    private String email;
    @Schema(name = "status", description = "账号状态：0 正常；1 锁定", defaultValue = "0")
    private Integer status;
    @Schema(name = "createdBy", description = "创建人")
    private String createdBy;
    @Schema(name = "createdTime", description = "创建时间")
    private LocalDateTime createdTime;
    @Schema(name = "updatedBy", description = "更新人")
    private String updatedBy;
    @Schema(name = "updatedTime", description = "更新时间")
    private LocalDateTime updatedTime;
    @Schema(name = "deleted", description = "逻辑删除：0 未删除；1 已删除", defaultValue = "0")
    private boolean deleted;
    private SysDept dept;
    private List<SysRole> roleList;
    private List<SysMenu> menuList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public List<SysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysUserDetail that)) return false;
        return deleted == that.deleted && Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(salt, that.salt) && Objects.equals(gender, that.gender) && Objects.equals(mobileNumber, that.mobileNumber) && Objects.equals(avatar, that.avatar) && Objects.equals(email, that.email) && Objects.equals(status, that.status) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdTime, that.createdTime) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(updatedTime, that.updatedTime) && Objects.equals(dept, that.dept) && Objects.equals(roleList, that.roleList) && Objects.equals(menuList, that.menuList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, password, salt, gender, mobileNumber, avatar, email, status, createdBy, createdTime, updatedBy, updatedTime, deleted, dept, roleList, menuList);
    }

    @Override
    public String toString() {
        return "SysUserDetail{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", deleted=" + deleted +
                ", dept=" + dept +
                ", roleList=" + roleList +
                ", menuList=" + menuList +
                '}';
    }
}
