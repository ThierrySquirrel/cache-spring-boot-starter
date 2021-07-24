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
 * ClassName: AbstractSendDeleteCacheMessageThread
 * Description:
 * date: 2020/5/24 3:05
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractSendDeleteCacheMessageThread implements Runnable {
    private CacheRedisTemplate cacheRedisTemplate;
    private String key;

    public AbstractSendDeleteCacheMessageThread(CacheRedisTemplate cacheRedisTemplate, String key) {
        this.cacheRedisTemplate = cacheRedisTemplate;
        this.key = key;
    }

    /**
     * asynchronousSendDeleteCacheMessage
     *
     * @param cacheRedisTemplate cacheRedisTemplate
     * @param key                key
     */
    protected abstract void asynchronousSendDeleteCacheMessage(CacheRedisTemplate cacheRedisTemplate, String key);

    @Override
    public void run() {
        asynchronousSendDeleteCacheMessage (this.cacheRedisTemplate,
                this.key);
    }
}
