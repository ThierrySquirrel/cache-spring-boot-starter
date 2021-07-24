/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.thierrysquirrel.cache.core.redis.consumer;

import com.github.thierrysquirrel.cache.core.factory.execution.CaffeineTemplateFactoryExecution;
import com.github.thierrysquirrel.cache.core.template.CaffeineTemplate;
import com.github.thierrysquirrel.cache.core.utils.ConsumerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.NonNull;

/**
 * ClassName: ExpiredConsumer
 * Description:
 * date: 2020/5/24 2:30
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class ExpiredConsumer implements MessageListener {
    private final CaffeineTemplate caffeineTemplate;

    public ExpiredConsumer(CaffeineTemplate caffeineTemplate) {
        this.caffeineTemplate = caffeineTemplate;
    }

    @Override
    public void onMessage(@NonNull Message message, byte[] pattern) {
        boolean cache = ConsumerUtils.isCache (message);
        if (cache) {
            CaffeineTemplateFactoryExecution.invalidate (caffeineTemplate, message);
            log.info ("ExpiredConsumer" + message);
        }
    }
}
