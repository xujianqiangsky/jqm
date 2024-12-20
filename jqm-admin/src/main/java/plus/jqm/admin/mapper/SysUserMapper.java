package plus.jqm.admin.mapper;

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

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import plus.jqm.api.domain.SysRolePermission;
import plus.jqm.api.domain.SysUser;
import plus.jqm.api.domain.SysUserDetail;

import java.util.List;

/**
 * 用户持久层
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUserDetail selectUserDetailById(@Param("id") Long id);

    List<SysRolePermission> selectRolePermissionByUserId(@Param("id") Long id);

    IPage<SysUserDetail> pageUserDetails(IPage<SysUserDetail> page);

    List<SysUser> checkUsernameAndMobileNumber(@Param("username") String username, @Param("mobileNumber") String mobileNumber);
}
