package plus.jqm.common.core.constant;

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
 * 日期模式
 *
 * @author xujianqiang
 * @date 2024/09/09
 */
public enum DatePattern {
    DATE_PATTERN("yyyy-MM-dd"),
    DATE_TIME_PATTERN("yyyy-MM-dd HH:mm:ss"),
    TIME_PATTERN("HH:mm:ss"),
    ;

    private final String pattern;

    DatePattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
