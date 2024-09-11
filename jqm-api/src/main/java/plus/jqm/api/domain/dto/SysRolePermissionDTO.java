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

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 角色权限传输对象
 *
 * @author xujianqiang
 * @date 2024/09/11
 */
public class SysRolePermissionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private List<String> permissionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysRolePermissionDTO that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(permissionList, that.permissionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, permissionList);
    }

    @Override
    public String toString() {
        return "SysRolePermissionDTO{" +
                "name='" + name + '\'' +
                ", permissionList=" + permissionList +
                '}';
    }
}
