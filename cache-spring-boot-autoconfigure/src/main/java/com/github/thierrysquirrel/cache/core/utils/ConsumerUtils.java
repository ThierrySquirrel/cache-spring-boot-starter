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
package com.github.thierrysquirrel.cache.core.utils;

import com.github.thierrysquirrel.cache.core.constant.RedisConsumerConstant;
import org.springframework.data.redis.connection.Message;

import java.util.Arrays;

/**
 * ClassName: ConsumerUtils
 * Description:
 * date: 2020/5/24 23:01
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ConsumerUtils {
    private ConsumerUtils() {
    }

    public static boolean isCache(Message message) {
        byte[] cacheIdentity = RedisConsumerConstant.REDIS_EXPIRED_CACHE_IDENTITY.getValue ();
        int cacheIdentityLength = cacheIdentity.length;
        byte[] body = message.getBody ();
        int readLength = body.length - cacheIdentityLength;
        byte[] cache = new byte[cacheIdentityLength];
        System.arraycopy (body, readLength, cache, 0, cacheIdentityLength);
        return Arrays.equals (cacheIdentity, cache);
    }
}
