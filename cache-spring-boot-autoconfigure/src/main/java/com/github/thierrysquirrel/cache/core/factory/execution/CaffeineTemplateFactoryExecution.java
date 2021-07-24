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
package com.github.thierrysquirrel.cache.core.factory.execution;

import com.github.thierrysquirrel.cache.core.factory.ConsumerMessageFactory;
import com.github.thierrysquirrel.cache.core.template.CaffeineTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;

/**
 * ClassName: CaffeineTemplateFactoryExecution
 * Description:
 * date: 2020/5/24 2:40
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class CaffeineTemplateFactoryExecution {
    private CaffeineTemplateFactoryExecution() {
    }
    public static void invalidate(CaffeineTemplate caffeineTemplate, Message message){
        String cacheKey = ConsumerMessageFactory.getCacheKey (message);
        caffeineTemplate.invalidate (cacheKey);
        log.info ("invalidate:{}", cacheKey);
    }
}
