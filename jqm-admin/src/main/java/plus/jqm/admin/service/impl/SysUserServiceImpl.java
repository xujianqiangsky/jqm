package plus.jqm.admin.service.impl;

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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import plus.jqm.admin.mapper.SysUserMapper;
import plus.jqm.admin.service.SysUserService;
import plus.jqm.api.domain.SysUser;
import plus.jqm.api.domain.vo.SysUserVO;

import java.util.Objects;

/**
 * 用户业务逻辑实现
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUserVO getUserById(Long id) {
        SysUserVO sysUserVO = new SysUserVO();
        SysUser sysUser = baseMapper.selectById(id);
        if (Objects.isNull(sysUser)) {
            return sysUserVO;
        }
        BeanUtils.copyProperties(sysUser, sysUserVO);
        return sysUserVO;
    }
}
