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
import cn.dev33.satoken.exception.SaTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import plus.jqm.common.core.constant.code.CommonErrorCode;
import plus.jqm.common.core.domain.Result;
import plus.jqm.common.security.handler.SecurityExceptionHandler;

/**
 * SaToken 异常处理器
 *
 * @author xujianqiang
 * @date 2024/09/14
 */
public class SaTokenExceptionHandler implements SecurityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(SaTokenExceptionHandler.class);

    @Override
    public boolean support(Throwable e) {
        return e instanceof SaTokenException;
    }

    @Override
    public Object handle(SaResponse response, Throwable throwable) {
        SaTokenException exception = (SaTokenException) throwable;
        LOG.warn("msg: {}", exception.getMessage(), exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return Result.failure(CommonErrorCode.SYSTEM_EXECUTION_ERROR);
    }
}
