package plus.jqm.oss.domain.dto;

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

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * 预签名传输对象
 *
 * @author xujianqiang
 * @date 2024/11/05
 */
public class PresignedDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String bucketName;
    private String key;
    private String filename;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PresignedDTO that)) return false;
        return Objects.equals(bucketName, that.bucketName) && Objects.equals(key, that.key) && Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucketName, key, filename);
    }

    @Override
    public String toString() {
        return "PresignedDTO{" +
                "bucketName='" + bucketName + '\'' +
                ", key='" + key + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
