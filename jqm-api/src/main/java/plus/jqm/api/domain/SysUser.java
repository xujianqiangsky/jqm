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

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用户数据对象
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
public class SysUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String name;
    private String password;
    private String salt;
    private Long deptId;
    private Integer gender;
    private String mobileNumber;
    private String avatar;
    private String email;
    private Integer status;
    private String createdBy;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    private String updatedBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private boolean deleted;

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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysUser sysUser)) return false;
        return deleted == sysUser.deleted && Objects.equals(id, sysUser.id) && Objects.equals(username, sysUser.username) && Objects.equals(name, sysUser.name) && Objects.equals(password, sysUser.password) && Objects.equals(salt, sysUser.salt) && Objects.equals(deptId, sysUser.deptId) && Objects.equals(gender, sysUser.gender) && Objects.equals(mobileNumber, sysUser.mobileNumber) && Objects.equals(avatar, sysUser.avatar) && Objects.equals(email, sysUser.email) && Objects.equals(status, sysUser.status) && Objects.equals(createdBy, sysUser.createdBy) && Objects.equals(createdTime, sysUser.createdTime) && Objects.equals(updatedBy, sysUser.updatedBy) && Objects.equals(updatedTime, sysUser.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, password, salt, deptId, gender, mobileNumber, avatar, email, status, createdBy, createdTime, updatedBy, updatedTime, deleted);
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", deptId=" + deptId +
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
                '}';
    }
}
