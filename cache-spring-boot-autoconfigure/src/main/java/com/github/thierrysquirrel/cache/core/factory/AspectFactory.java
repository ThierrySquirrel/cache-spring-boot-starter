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

import com.github.thierrysquirrel.cache.annotation.CacheParameter;
import com.github.thierrysquirrel.cache.core.utils.AspectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: AspectFactory
 * Description:
 * date: 2020/5/24 3:39
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class AspectFactory {
    private AspectFactory() {
    }

    public static String paramsAnnotationToString(ProceedingJoinPoint proceedingJoinPoint) {
        Parameter[] params = AspectUtils.getParams (proceedingJoinPoint);
        Object[] args = proceedingJoinPoint.getArgs ();
        List<Object> argsList = new ArrayList<> ();
        for (int i = 0; i < params.length; i++) {
            CacheParameter annotation = params[i].getAnnotation (CacheParameter.class);
            if (ObjectUtils.isEmpty (annotation)) {
                continue;
            }
            argsList.add (args[i]);
        }
        return argsList.toString ();
    }

}
