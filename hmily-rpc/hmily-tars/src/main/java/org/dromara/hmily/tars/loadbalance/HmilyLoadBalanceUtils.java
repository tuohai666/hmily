/*
 * Copyright 2017-2021 Dromara.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.hmily.tars.loadbalance;

import com.google.common.collect.Maps;
import com.qq.tars.rpc.common.Invoker;
import com.qq.tars.rpc.common.Url;
import org.dromara.hmily.common.enums.HmilyActionEnum;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.dromara.hmily.core.context.HmilyTransactionContext;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The type hmily tars load balance utils.
 *
 * @author tydhot
 */
public class HmilyLoadBalanceUtils {

    private static final Map<String, Url> URL_MAP = Maps.newConcurrentMap();
    
    /**
     * Do select invoker.
     *
     * @param <T>            the type parameter
     * @param defaultInvoker the default invoker
     * @param invokers       the invokers
     * @return the invoker
     */
    public static <T> Invoker<T> doSelect(final Invoker<T> defaultInvoker, final List<Invoker<T>> invokers) {
        final HmilyTransactionContext hmilyTransactionContext = HmilyContextHolder.get();
        if (Objects.isNull(hmilyTransactionContext)) {
            return defaultInvoker;
        }
        //if try
        String key = defaultInvoker.getApi().getName();
        if (hmilyTransactionContext.getAction() == HmilyActionEnum.TRYING.getCode()) {
            URL_MAP.put(key, defaultInvoker.getUrl());
            return defaultInvoker;
        }
        final Url orlUrl = URL_MAP.get(key);
        URL_MAP.remove(key);
        if (Objects.nonNull(orlUrl)) {
            for (Invoker<T> inv : invokers) {
                if (Objects.equals(inv.getUrl(), orlUrl)) {
                    return inv;
                }
            }
        }
        return defaultInvoker;
    }
}
