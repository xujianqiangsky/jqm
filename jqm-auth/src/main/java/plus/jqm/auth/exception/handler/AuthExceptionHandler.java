package plus.jqm.auth.exception.handler;

/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
import plus.jqm.auth.constant.AuthErrorCode;
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import cn.dev33.satoken.exception.NotPermissionException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import plus.jqm.common.core.constant.code.CommonErrorCode;
import plus.jqm.common.core.domain.Result;
import plus.jqm.common.security.constant.AuthErrorCode;
import plus.jqm.common.security.util.SecurityUtils;

/**
 * Auth 异常处理
 *
 * @author xujianqiang
 * @date 2024/09/09
 */
@RestControllerAdvice
public class AuthExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(AuthExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = NotPermissionException.class)
    public Result<Object> handleNotPermissionException(NotPermissionException exception) {
        LOG.warn("username: {} and msg: {}", SecurityUtils.getLoginUsername(), exception.getMessage(), exception);
        return Result.failure(AuthErrorCode.ACCESS_UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Result<Object> handleException(HttpServletRequest request, Exception exception) {
        LOG.error("Request: {} and msg: {}", request, exception.getMessage(), exception);
        return Result.failure(CommonErrorCode.SYSTEM_EXECUTION_ERROR);
    }
}