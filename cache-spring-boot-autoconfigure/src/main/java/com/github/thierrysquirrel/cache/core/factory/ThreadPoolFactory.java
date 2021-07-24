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

import com.github.thierrysquirrel.cache.core.constant.ThreadPoolNameConstant;
import com.github.thierrysquirrel.cache.core.constant.ThreadPoolSizeConstant;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ThreadPoolFactory
 * Description:
 * date: 2020/5/24 3:13
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ThreadPoolFactory {
    private ThreadPoolFactory() {
    }

    public static ThreadPoolExecutor createRedisAsynchronousTaskThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolNameConstant.REDIS_ASYNCHRONOUS_TASK.getValue ()).build ();
        return new ThreadPoolExecutor (ThreadPoolSizeConstant.REDIS_ASYNCHRONOUS_TASK_CORE_POOL_SIZE.getValue (),
                ThreadPoolSizeConstant.REDIS_ASYNCHRONOUS_TASK_MAXIMUM_POOL_SIZE.getValue (),
                ThreadPoolSizeConstant.REDIS_ASYNCHRONOUS_TASK_KEEP_ALIVE_TIME.getValue (),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<> (ThreadPoolSizeConstant.REDIS_ASYNCHRONOUS_TASK_CAPACITY.getValue ()),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy ()
        );
    }
}
