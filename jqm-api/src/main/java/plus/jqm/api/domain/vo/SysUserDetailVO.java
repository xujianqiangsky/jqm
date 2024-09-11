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
import java.util.List;
import java.util.Objects;

/**
 * 用户详细信息展示对象
 *
 * @author xujianqiang
 * @date 2024/09/10
 */
public class SysUserDetailVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String name;
    private Integer gender;
    private String mobileNumber;
    private String avatar;
    private String email;
    private Integer status;
    private String createdBy;
    private LocalDateTime createdTime;
    private String updatedBy;
    private LocalDateTime updatedTime;
    private SysDeptVO dept;
    private List<SysRoleVO> roleList;
    private List<SysMenuVO> menuList;

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

    public SysDeptVO getDept() {
        return dept;
    }

    public void setDept(SysDeptVO dept) {
        this.dept = dept;
    }

    public List<SysRoleVO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRoleVO> roleList) {
        this.roleList = roleList;
    }

    public List<SysMenuVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenuVO> menuList) {
        this.menuList = menuList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysUserDetailVO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(mobileNumber, that.mobileNumber) && Objects.equals(avatar, that.avatar) && Objects.equals(email, that.email) && Objects.equals(status, that.status) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdTime, that.createdTime) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(updatedTime, that.updatedTime) && Objects.equals(dept, that.dept) && Objects.equals(roleList, that.roleList) && Objects.equals(menuList, that.menuList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, gender, mobileNumber, avatar, email, status, createdBy, createdTime, updatedBy, updatedTime, dept, roleList, menuList);
    }

    @Override
    public String toString() {
        return "SysUserDetailVO{" +
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
                ", dept=" + dept +
                ", roleList=" + roleList +
                ", menuList=" + menuList +
                '}';
    }
}
