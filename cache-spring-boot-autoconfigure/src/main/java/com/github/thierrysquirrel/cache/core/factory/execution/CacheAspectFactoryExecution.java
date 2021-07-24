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

import com.github.thierrysquirrel.cache.core.exception.CacheException;
import com.github.thierrysquirrel.cache.core.factory.CacheAspectFactory;
import com.github.thierrysquirrel.cache.core.redis.template.CacheRedisTemplate;
import com.github.thierrysquirrel.cache.core.template.CaffeineTemplate;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * ClassName: CacheAspectFactoryExecution
 * Description:
 * date: 2020/5/24 23:08
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class CacheAspectFactoryExecution {
    private CacheAspectFactoryExecution() {
    }

    public static Object loadCache(ProceedingJoinPoint proceedingJoinPoint, CacheRedisTemplate cacheRedisTemplate, CaffeineTemplate caffeineTemplate, String key) throws CacheException {
        return CacheAspectFactory.loadCaffeineCache (proceedingJoinPoint, cacheRedisTemplate, caffeineTemplate, key);
    }
    public static Object clearCache(ProceedingJoinPoint proceedingJoinPoint, CacheRedisTemplate cacheRedisTemplate, CaffeineTemplate caffeineTemplate, String key) throws CacheException {
        return CacheAspectFactory.clearCache (proceedingJoinPoint, cacheRedisTemplate, caffeineTemplate, key);
    }
}
