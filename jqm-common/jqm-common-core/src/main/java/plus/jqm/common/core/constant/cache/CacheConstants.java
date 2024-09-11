package plus.jqm.common.core.constant.cache;

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
 * 缓存键值常量
 *
 * @author xujianqiang
 * @date 2024/09/11
 */
public enum CacheConstants {
    ROLE_CACHE_KEY_PREFIX("jqm:id-find-role:"),
    PERMISSION_CACHE_KEY_PREFIX("jqm:role-find-permission:")
    ;
    private final String key;

    CacheConstants(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
