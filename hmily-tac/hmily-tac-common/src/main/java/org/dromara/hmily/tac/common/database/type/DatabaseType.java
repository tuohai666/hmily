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

package org.dromara.hmily.tac.common.database.type;

import java.util.Collection;
import org.dromara.hmily.tac.common.database.metadata.DataSourceMetaData;

/**
 * Database type.
 */
public interface DatabaseType {
    
    /**
     * Get database name.
     * 
     * @return database name
     */
    String getName();
    
    /**
     * Get alias of JDBC URL prefixes.
     * 
     * @return Alias of JDBC URL prefixes
     */
    Collection<String> getJdbcUrlPrefixes();
    
    /**
     * Get data source meta data.
     * 
     * @param url URL of data source
     * @param username username of data source
     * @return data source meta data
     */
    DataSourceMetaData getDataSourceMetaData(String url, String username);
}
