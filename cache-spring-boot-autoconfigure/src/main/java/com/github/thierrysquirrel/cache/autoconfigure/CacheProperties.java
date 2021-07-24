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
package com.github.thierrysquirrel.cache.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: CacheAutoconfigure
 * Description:
 * date: 2020/5/24 1:31
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
@ConfigurationProperties(prefix = CacheProperties.CACHE_PREFIX)
public class CacheProperties {
    public static final String CACHE_PREFIX = "cache";
    /**
     * Initial Cache Capacity
     * 缓存初始容量
     */
    private int initialCacheCapacity = 4096;
    /**
     * Maximum Cache Capacity
     * 缓存最大容量
     */
    private int maximumCacheCapacity = 8192;
    /**
     * Expiration Time
     * 过期时间单位秒
     */
    private int expirationTime = 60 * 10;
}
