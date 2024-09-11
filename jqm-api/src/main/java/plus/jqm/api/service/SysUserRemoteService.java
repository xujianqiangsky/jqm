package plus.jqm.api.service;

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

import plus.jqm.api.domain.dto.SysRolePermissionDTO;
import plus.jqm.api.domain.dto.SysUserDTO;
import plus.jqm.common.core.domain.Result;

import java.util.List;

/**
 * 用户信息远程调用服务
 *
 * @author xujianqiang
 * @date 2024/09/08
 */
public interface SysUserRemoteService {
    Result<SysUserDTO> getUserByUsername(String username);
    Result<List<SysRolePermissionDTO>> getUserRoleAndPermissionById(Long id);
}
