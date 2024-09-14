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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * SaToken 异常处理对象构建器
 *
 * @author xujianqiang
 * @date 2024/09/14
 */
public class SecurityExceptionHandlerBuilder {
    private final List<SecurityExceptionHandler> exceptionHandlerList = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void addExceptionHandler(SecurityExceptionHandler exceptionHandler) {
        this.exceptionHandlerList.add(exceptionHandler);
    }

    public void addExceptionHandlers(List<SecurityExceptionHandler> exceptionHandlerList) {
        this.exceptionHandlerList.addAll(exceptionHandlerList);
    }

    public SaTokenSecurityExceptionHandler build() {
        return new SaTokenSecurityExceptionHandler(objectMapper, exceptionHandlerList);
    }
}
