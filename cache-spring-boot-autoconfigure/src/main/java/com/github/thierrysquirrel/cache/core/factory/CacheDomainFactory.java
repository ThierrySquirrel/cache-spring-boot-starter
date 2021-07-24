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

import com.github.thierrysquirrel.cache.core.domain.CacheDomain;

/**
 * ClassName: CacheDomainFactory
 * Description:
 * date: 2020/5/24 1:58
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class CacheDomainFactory {
    private CacheDomainFactory() {
    }

    public static CacheDomain createKeyCacheDomain(String key) {
        CacheDomain cacheDomain = new CacheDomain ();
        cacheDomain.setKey (key);
        return cacheDomain;
    }

    public static CacheDomain createValueCacheDomain(Object value) {
        CacheDomain cacheDomain = new CacheDomain ();
        cacheDomain.setValue (value);
        return cacheDomain;
    }
}
