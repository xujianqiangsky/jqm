package plus.jqm.admin.controller;

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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.jqm.admin.service.SysUserService;
import plus.jqm.api.domain.vo.SysUserDetailVO;
import plus.jqm.api.domain.vo.SysUserVO;
import plus.jqm.common.core.domain.Result;

/**
 * 用户控制器
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysUserController {
    private final SysUserService userService;

    public SysUserController(SysUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public Result<SysUserVO> getCurrentUserInfo() {
        SysUserVO userVO = userService.getUserById(StpUtil.getLoginId(-1L));
        return Result.success(userVO);
    }

    @GetMapping("/detail")
    public Result<SysUserDetailVO> getCurrentUserDetailInfo() {
        SysUserDetailVO userDetailVO = userService.getUserDetailById(StpUtil.getLoginId(-1L));
        return Result.success(userDetailVO);
    }
}
