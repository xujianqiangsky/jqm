package plus.jqm.common.security.handler.impl;

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

import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.exception.SameTokenInvalidException;
import org.springframework.http.HttpStatus;
import plus.jqm.common.core.domain.Result;
import plus.jqm.common.security.constant.code.AuthErrorCode;
import plus.jqm.common.security.handler.SecurityExceptionHandler;

/**
 * SameToken 异常处理器
 *
 * @author xujianqiang
 * @date 2024/09/14
 */
public class SameTokenInvalidExceptionHandler implements SecurityExceptionHandler {
    @Override
    public boolean support(Throwable throwable) {
        return throwable instanceof SameTokenInvalidException;
    }

    @Override
    public Object handle(SaResponse response, Throwable throwable) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return Result.failure(AuthErrorCode.INVALID_SAME_TOKEN);
    }
}
