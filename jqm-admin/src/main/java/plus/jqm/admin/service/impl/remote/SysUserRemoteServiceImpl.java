package plus.jqm.admin.service.impl.remote;

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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import plus.jqm.admin.mapper.SysUserMapper;
import plus.jqm.api.domain.SysUser;
import plus.jqm.api.service.SysUserRemoteService;
import plus.jqm.common.core.domain.Result;

/**
 * 用户信息远程调用服务实现
 *
 * @author xujianqiang
 * @date 2024/09/08
 */
@DubboService
public class SysUserRemoteServiceImpl implements SysUserRemoteService {
    private SysUserMapper sysUserMapper;

    public SysUserRemoteServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public Result<SysUser> getUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaQueryWrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserMapper.selectOne(sysUserLambdaQueryWrapper);
        return Result.success(sysUser);
    }
}
