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
package com.github.thierrysquirrel.cache.core.factory;

import com.github.thierrysquirrel.cache.core.constant.CacheRedisConstant;
import com.github.thierrysquirrel.cache.core.redis.consumer.DelCacheConsumer;
import com.github.thierrysquirrel.cache.core.redis.consumer.ExpiredConsumer;
import com.github.thierrysquirrel.cache.core.template.CaffeineTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * ClassName: RedisMessageListenerContainerFactory
 * Description:
 * date: 2020/5/24 2:27
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class RedisMessageListenerContainerFactory {
    private RedisMessageListenerContainerFactory() {
    }
    public static RedisMessageListenerContainer createRedisMessageListenerContainer(RedisConnectionFactory connectionFactory, CaffeineTemplate caffeineTemplate){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer ();
        connectionFactory.getConnection ().setConfig (CacheRedisConstant.EXPIRED_CACHE_CONFIGURATION_KEY.getValue (),CacheRedisConstant.EXPIRED_CACHE_CONFIGURATION_VALUE.getValue ());
        container.setConnectionFactory (connectionFactory);
        container.addMessageListener (new DelCacheConsumer (caffeineTemplate), new PatternTopic (CacheRedisConstant.DELETE_CACHE_TOPIC.getValue ()));
        container.addMessageListener (new ExpiredConsumer (caffeineTemplate), new PatternTopic (CacheRedisConstant.EXPIRED_CACHE_TOPIC.getValue ()));

        return container;
    }
}
