package plus.jqm.auth.domain.vo;

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

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * token 信息
 *
 * @author xujianqiang
 * @date 2024/09/08
 */
@Schema(description = "token 信息")
public class TokenInfoVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "tokenValue", description = "令牌")
    private String tokenValue;
    @Schema(name = "loginDevice", description = "登录设备标识")
    private String loginDevice;

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getLoginDevice() {
        return loginDevice;
    }

    public void setLoginDevice(String loginDevice) {
        this.loginDevice = loginDevice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenInfoVO that)) return false;
        return Objects.equals(tokenValue, that.tokenValue) && Objects.equals(loginDevice, that.loginDevice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenValue, loginDevice);
    }

    @Override
    public String toString() {
        return "TokenInfoVO{" +
                "tokenValue='" + tokenValue + '\'' +
                ", loginDevice='" + loginDevice + '\'' +
                '}';
    }
}
