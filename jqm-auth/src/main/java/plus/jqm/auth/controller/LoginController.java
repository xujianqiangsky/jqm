package plus.jqm.auth.controller;

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

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import plus.jqm.auth.domain.LoginUser;
import plus.jqm.auth.domain.vo.TokenInfoVO;
import plus.jqm.auth.service.LoginService;
import plus.jqm.common.core.domain.Result;

/**
 * 用户认证模块
 *
 * @author xujianqiang
 * @date 2024/09/07
 */
@Tag(name = "用户认证模块")
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 用户登录
     *
     * @param loginUser
     * @return
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<TokenInfoVO> login(@RequestBody LoginUser loginUser) {
        return loginService.login(loginUser);
    }

    /**
     * 用户登出
     *
     * @return
     */
    @Operation(summary = "用户登出")
    @GetMapping("/logout")
    public Result<String> logout() {
        StpUtil.logout(StpUtil.getLoginId(), StpUtil.getLoginDevice());
        return Result.success("ok");
    }
}
