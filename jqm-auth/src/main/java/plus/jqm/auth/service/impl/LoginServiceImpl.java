package plus.jqm.auth.service.impl;

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

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import io.micrometer.common.util.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import plus.jqm.api.domain.dto.SysUserDTO;
import plus.jqm.api.service.SysUserRemoteService;
import plus.jqm.auth.domain.LoginUser;
import plus.jqm.auth.domain.vo.TokenInfoVO;
import plus.jqm.auth.service.LoginService;
import plus.jqm.common.core.constant.TokenExtraConstants;
import plus.jqm.common.core.domain.Result;
import plus.jqm.common.security.constant.AuthErrorCode;

/**
 * 用户登录服务实现
 *
 * @author xujianqiang
 * @date 2024/09/08
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

    @DubboReference
    private SysUserRemoteService userRemoteService;

    @Override
    public Result<TokenInfoVO> login(LoginUser loginUser) {
        if (loginUser == null || StringUtils.isEmpty(loginUser.getUsername()) || StringUtils.isEmpty(loginUser.getPassword())) {
            return Result.failure(AuthErrorCode.USERNAME_OR_PASSWORD_ERROR);
        }

        // 获取用户信息
        Result<SysUserDTO> userDTOResult = userRemoteService.getUserByUsername(loginUser.getUsername());
        SysUserDTO originalUser = userDTOResult.getData();
        if (StringUtils.isEmpty(originalUser.getUsername())) {
            LOG.warn("Username: {} and exception: Username not found.", loginUser.getUsername());
            return Result.failure(AuthErrorCode.USERNAME_OR_PASSWORD_ERROR);
        }

        // 验证密码
        if (BCrypt.checkpw(loginUser.getPassword(), originalUser.getPassword())) {
            // 获取设备标识
            UserAgent userAgent = UserAgentUtil.parse(SaHolder.getRequest().getHeader("User-Agent"));
            SaLoginModel saLoginModel = new SaLoginModel();
            saLoginModel.setDevice(userAgent.getPlatform().getName());
            saLoginModel.setExtra(TokenExtraConstants.CURRENT_USERNAME_KEY.getKey(), loginUser.getUsername());
            // 登录
            StpUtil.login(originalUser.getId(), saLoginModel);
            TokenInfoVO tokenInfoVO = new TokenInfoVO();
            BeanUtils.copyProperties(StpUtil.getTokenInfo(), tokenInfoVO);
            return Result.success(tokenInfoVO);
        }

        LOG.warn("Username: {} and exception: Invalid password.", loginUser.getUsername());
        return Result.failure(AuthErrorCode.USERNAME_OR_PASSWORD_ERROR);
    }
}
