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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.thierrysquirrel.cache.autoconfigure.CacheProperties;
import com.github.thierrysquirrel.cache.core.domain.CacheDomain;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: CaffeineFactory
 * Description:
 * date: 2020/5/24 2:10
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class CaffeineFactory {
    private CaffeineFactory() {
    }

    public static Cache<String, CacheDomain> createCaffeine(CacheProperties cacheProperties) {
        return Caffeine.newBuilder ()
                .initialCapacity (cacheProperties.getInitialCacheCapacity ())
                .maximumSize (cacheProperties.getMaximumCacheCapacity ())
                .expireAfterWrite (cacheProperties.getExpirationTime (), TimeUnit.SECONDS)
                .build ();
    }
}
