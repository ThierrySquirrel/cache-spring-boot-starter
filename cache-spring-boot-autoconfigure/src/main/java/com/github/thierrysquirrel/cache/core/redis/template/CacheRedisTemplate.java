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
package com.github.thierrysquirrel.cache.core.redis.template;

import com.github.thierrysquirrel.cache.autoconfigure.CacheProperties;
import com.github.thierrysquirrel.cache.core.constant.CacheRedisConstant;
import com.github.thierrysquirrel.cache.core.constant.RedisConsumerConstant;
import com.github.thierrysquirrel.cache.core.domain.CacheDomain;
import com.github.thierrysquirrel.cache.core.factory.CacheDomainFactory;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: CacheRedisTemplate
 * Description:
 * date: 2020/5/24 1:44
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class CacheRedisTemplate extends RedisTemplate<CacheDomain, CacheDomain> {
    private CacheProperties cacheProperties;

    public CacheRedisTemplate(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    public void setCache(String key, Object value) {
        CacheDomain keyCacheDomain = CacheDomainFactory.createKeyCacheDomain (key);
        keyCacheDomain.setRedisExpiredCacheIdentity (RedisConsumerConstant.REDIS_EXPIRED_CACHE_IDENTITY.getValue ());
        CacheDomain valueCacheDomain = CacheDomainFactory.createValueCacheDomain (value);
        this.opsForValue ().set (keyCacheDomain, valueCacheDomain, cacheProperties.getExpirationTime (), TimeUnit.SECONDS);
    }

    public Object getCache(String key) {
        CacheDomain keyCacheDomain = CacheDomainFactory.createKeyCacheDomain (key);
        CacheDomain cacheDomain = this.opsForValue ().get (keyCacheDomain);
        if (ObjectUtils.isEmpty (cacheDomain)) {
            return null;
        }
        return cacheDomain.getValue ();
    }

    public void sendDeleteCacheMessage(String key) {
        CacheDomain keyCacheDomain = CacheDomainFactory.createKeyCacheDomain (key);
        this.convertAndSend (CacheRedisConstant.DELETE_CACHE_TOPIC.getValue (), keyCacheDomain);
    }

}
