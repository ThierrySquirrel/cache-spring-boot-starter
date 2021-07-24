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
package com.github.thierrysquirrel.cache.core.constant;

/**
 * ClassName: CacheRedisConstant
 * Description:
 * date: 2020/5/24 1:51
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public enum CacheRedisConstant {
    /**
     * Expired Cache Configuration Key
     */
    EXPIRED_CACHE_CONFIGURATION_KEY ("notify-keyspace-events"),
    /**
     * Expired Cache Configuration Value
     */
    EXPIRED_CACHE_CONFIGURATION_VALUE ("Ex"),
    /**
     * ExpiredCacheTopic
     */
    EXPIRED_CACHE_TOPIC ("__keyevent@*__:expired"),
    /**
     * Delete Cache Topic
     */
    DELETE_CACHE_TOPIC ("delete_cache");

    private final String value;

    CacheRedisConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
