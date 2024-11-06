package plus.jqm.oss.config;

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

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * 对象存储属性配置
 *
 * @author xujianqiang
 * @date 2024/11/02
 */
@ConfigurationProperties(prefix = "oss")
public class OSSProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String endpoint;
    private String accessKey;
    private String secretKey;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OSSProperties that)) return false;
        return Objects.equals(endpoint, that.endpoint) && Objects.equals(accessKey, that.accessKey) && Objects.equals(secretKey, that.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endpoint, accessKey, secretKey);
    }

    @Override
    public String toString() {
        return "OSSProperties{" +
                "endpoint='" + endpoint + '\'' +
                '}';
    }
}
