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

import com.github.thierrysquirrel.cache.core.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.cache.core.factory.execution.ThreadPoolExecutorExecution;
import com.github.thierrysquirrel.cache.core.redis.template.CacheRedisTemplate;
import com.github.thierrysquirrel.cache.thread.execution.SendDeleteCacheMessageThreadExecution;
import com.github.thierrysquirrel.cache.thread.execution.SetCacheThreadExecution;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: RedisAsynchronousTaskFactory
 * Description:
 * date: 2020/5/24 4:02
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class RedisAsynchronousTaskFactory {
    private RedisAsynchronousTaskFactory() {
    }

    public static void setCache(CacheRedisTemplate cacheRedisTemplate, String key, Object value) {
        SetCacheThreadExecution setCacheThreadExecution = new SetCacheThreadExecution (cacheRedisTemplate, key, value);
        ThreadPoolExecutor redisAsynchronousTaskThreadPool = ThreadPoolFactoryConstant.REDIS_ASYNCHRONOUS_TASK_THREAD_POOL;
        ThreadPoolExecutorExecution.statsThread (redisAsynchronousTaskThreadPool, setCacheThreadExecution);
    }

    public static void sendDeleteCacheMessage(CacheRedisTemplate cacheRedisTemplate, String key) {
        SendDeleteCacheMessageThreadExecution sendDeleteCacheMessageThreadExecution = new SendDeleteCacheMessageThreadExecution (cacheRedisTemplate, key);
        ThreadPoolExecutor redisAsynchronousTaskThreadPool = ThreadPoolFactoryConstant.REDIS_ASYNCHRONOUS_TASK_THREAD_POOL;
        ThreadPoolExecutorExecution.statsThread (redisAsynchronousTaskThreadPool, sendDeleteCacheMessageThreadExecution);

    }
}
