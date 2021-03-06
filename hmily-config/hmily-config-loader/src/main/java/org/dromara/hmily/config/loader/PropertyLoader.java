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

package org.dromara.hmily.config.loader;

import java.io.InputStream;
import java.util.List;
import org.dromara.hmily.config.loader.property.PropertyKeySource;

/**
 * The interface Property loader.
 *
 * @author xiaoyu
 */
public interface PropertyLoader {
    
    /**
     * Check file boolean.
     *
     * @param fileName the file name
     * @return the boolean
     */
    boolean checkFile(String fileName);
    
    /**
     * Load list.
     *
     * @param name   the name
     * @param stream the stream
     * @return the list
     */
    List<PropertyKeySource<?>> load(String name, InputStream stream);
}
