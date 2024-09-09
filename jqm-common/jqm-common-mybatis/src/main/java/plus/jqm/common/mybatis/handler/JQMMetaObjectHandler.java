package plus.jqm.common.mybatis.handler;

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

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * 字段填充处理器
 *
 * @author xujianqiang
 * @date 2024/09/05
 */
public class JQMMetaObjectHandler implements MetaObjectHandler {
    private static final Logger LOG = LoggerFactory.getLogger(JQMMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("insert fill start");
        }

        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "deleted", boolean.class, false);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("update fill start");
        }
        this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now());
    }
}
