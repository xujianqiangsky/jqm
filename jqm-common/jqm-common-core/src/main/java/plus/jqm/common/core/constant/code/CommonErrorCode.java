package plus.jqm.common.core.constant.code;

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

/**
 * 常见响应错误码
 *
 * @author xujianqiang
 * @date 2024/09/09
 */
public enum CommonErrorCode implements ErrorCode {
    SUCCESS("00000", "ok"),
    SYSTEM_EXECUTION_ERROR("B0001", "系统执行出错")
    ;

    private final String code;
    private final String msg;

    CommonErrorCode(String code, String msg) {
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
