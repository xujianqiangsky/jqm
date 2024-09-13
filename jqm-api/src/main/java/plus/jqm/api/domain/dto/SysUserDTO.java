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
 * 用户数据传输对象
 *
 * @author xujianqiang
 * @date 2024/09/10
 */
@Schema(description = "用户数据传输对象")
public class SysUserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "用户 id")
    private Long id;
    @Schema(name = "username", description = "用户名")
    private String username;
    @Schema(name = "password", description = "用户密码")
    private String password;
    @Schema(name = "name", description = "姓名")
    private String name;
    @Schema(name = "deptId", description = "部门 id")
    private Long deptId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysUserDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(deptId, that.deptId) && Objects.equals(gender, that.gender) && Objects.equals(mobileNumber, that.mobileNumber) && Objects.equals(avatar, that.avatar) && Objects.equals(email, that.email) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, deptId, gender, mobileNumber, avatar, email, status);
    }

    @Override
    public String toString() {
        return "SysUserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", deptId=" + deptId +
                ", gender=" + gender +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
