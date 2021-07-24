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

import com.github.thierrysquirrel.cache.core.exception.CacheException;
import com.github.thierrysquirrel.cache.core.redis.template.CacheRedisTemplate;
import com.github.thierrysquirrel.cache.core.template.CaffeineTemplate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.ObjectUtils;

/**
 * ClassName: CacheAspectFactory
 * Description:
 * date: 2020/5/24 3:42
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class CacheAspectFactory {
    private CacheAspectFactory() {
    }

    public static Object loadCaffeineCache(ProceedingJoinPoint proceedingJoinPoint, CacheRedisTemplate cacheRedisTemplate, CaffeineTemplate caffeineTemplate, String key) throws CacheException {
        Object cache = caffeineTemplate.get (key);
        if (ObjectUtils.isEmpty (cache)) {
            return loadRedisCache (proceedingJoinPoint, cacheRedisTemplate, caffeineTemplate, key);
        }
        return cache;
    }

    private static Object loadRedisCache(ProceedingJoinPoint proceedingJoinPoint, CacheRedisTemplate cacheRedisTemplate, CaffeineTemplate caffeineTemplate, String key) throws CacheException {
        Object cache = cacheRedisTemplate.getCache (key);
        if (ObjectUtils.isEmpty (cache)) {
            return releaseTask (proceedingJoinPoint, cacheRedisTemplate, caffeineTemplate, key);
        }
        caffeineTemplate.put (key, cache);
        return cache;
    }

    private static Object releaseTask(ProceedingJoinPoint proceedingJoinPoint, CacheRedisTemplate cacheRedisTemplate, CaffeineTemplate caffeineTemplate, String key) throws CacheException {
        Object proceed;
        try {
            proceed = proceedingJoinPoint.proceed ();
            caffeineTemplate.put (key, proceed);
            RedisAsynchronousTaskFactory.setCache (cacheRedisTemplate, key, proceed);
        } catch (Throwable throwable) {
            log.error (throwable.toString ());
            throw new CacheException ("Method execution failed!", throwable);
        }

        return proceed;
    }

    public static Object clearCache(ProceedingJoinPoint proceedingJoinPoint, CacheRedisTemplate cacheRedisTemplate, CaffeineTemplate caffeineTemplate, String key) throws CacheException {
        Object proceed;
        try {
            proceed = proceedingJoinPoint.proceed ();
            caffeineTemplate.invalidate (key);
            RedisAsynchronousTaskFactory.sendDeleteCacheMessage (cacheRedisTemplate, key);
        } catch (Throwable throwable) {
            log.error (throwable.toString ());
            throw new CacheException ("Method execution failed!", throwable);
        }
        return proceed;
    }
}
