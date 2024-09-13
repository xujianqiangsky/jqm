package plus.jqm.common.security.constant.code;

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

import plus.jqm.common.core.constant.code.ErrorCode;

/**
 * 身份认证错误码
 *
 * @author xujianqiang
 * @date 2024/09/09
 */
public enum AuthErrorCode implements ErrorCode {
    NOT_LOGIN("A0201", "用户未登录"),
    USERNAME_OR_PASSWORD_ERROR("A0202", "用户名或密码错误"),
    ACCESS_UNAUTHORIZED("A0301", "访问未授权"),
    INVALID_SAME_TOKEN("A0302", "无效的 Same-Token"),
    ;

    private final String code;
    private final String msg;

    AuthErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
