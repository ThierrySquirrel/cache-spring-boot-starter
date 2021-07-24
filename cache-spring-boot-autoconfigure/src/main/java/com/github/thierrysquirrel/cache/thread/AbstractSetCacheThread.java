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
package com.github.thierrysquirrel.cache.thread;

import com.github.thierrysquirrel.cache.core.redis.template.CacheRedisTemplate;
import lombok.Data;

/**
 * ClassName: AbstractSetCacheThread
 * Description:
 * date: 2020/5/24 3:01
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractSetCacheThread implements Runnable {
    private CacheRedisTemplate cacheRedisTemplate;
    private String key;
    private Object value;

    public AbstractSetCacheThread(CacheRedisTemplate cacheRedisTemplate, String key, Object value) {
        this.cacheRedisTemplate = cacheRedisTemplate;
        this.key = key;
        this.value = value;
    }

    /**
     * asynchronousSetCache
     *
     * @param cacheRedisTemplate cacheRedisTemplate
     * @param key                key
     * @param value              value
     */
    protected abstract void asynchronousSetCache(CacheRedisTemplate cacheRedisTemplate, String key, Object value);

    @Override
    public void run() {
        asynchronousSetCache (this.cacheRedisTemplate,
                this.key,
                this.value);
    }
}
