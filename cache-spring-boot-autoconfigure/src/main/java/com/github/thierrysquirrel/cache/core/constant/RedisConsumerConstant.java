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
 * ClassName: RedisConsumerConstant
 * Description:
 * date: 2020/5/24 22:58
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public enum RedisConsumerConstant {
    /**
     * Redis Expired Cache Identity
     */
    REDIS_EXPIRED_CACHE_IDENTITY (new byte[]{40, 47, 47, -30, -106, -67, 47, 47, 41});
    private final byte[] value;

    RedisConsumerConstant(byte[] value) {
        this.value = value;
    }

    public byte[] getValue() {
        return value;
    }
}
