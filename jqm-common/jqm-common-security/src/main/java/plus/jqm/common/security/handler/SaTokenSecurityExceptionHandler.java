package plus.jqm.common.security.handler;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * SaToken 全局异常处理器
 *
 * @author xujianqiang
 * @date 2024/09/14
 */
public class SaTokenSecurityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(SaTokenSecurityExceptionHandler.class);
    private final List<SecurityExceptionHandler> exceptionHandlerList;
    private final ObjectMapper objectMapper;

    public SaTokenSecurityExceptionHandler(ObjectMapper objectMapper, List<SecurityExceptionHandler> exceptionHandlerList) {
        this.objectMapper = objectMapper;
        this.exceptionHandlerList = exceptionHandlerList;
    }

    public String handle(SaResponse response, Throwable throwable) {
        for (SecurityExceptionHandler exceptionHandler : exceptionHandlerList) {
            if (exceptionHandler.support(throwable)) {
                Object obj = exceptionHandler.handle(response, throwable);
                try {
                    return objectMapper.writeValueAsString(obj);
                } catch (JsonProcessingException exception) {
                    LOG.warn("msg: {}", exception.getMessage(), exception);
                    return "";
                }
            }
        }
        return "";
    }
}
