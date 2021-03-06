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

package org.dromara.hmily.tac.metadata.loader;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hmily.tac.common.database.dialect.DatabaseMetaDataDialectHandlerFactory;
import org.dromara.hmily.tac.common.database.type.DatabaseType;
import org.dromara.hmily.tac.metadata.connection.MetaDataConnectionAdapter;
import org.dromara.hmily.tac.metadata.model.TableMetaData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


/**
 * Physical table meta data loader.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TableMetaDataLoader {
    
    /**
     * Load table meta data.
     *
     * @param connectionAdapter connection adapter
     * @param tableNamePattern table name pattern
     * @param databaseType database type
     * @return table meta data
     * @throws SQLException SQL exception
     */
    public static Optional<TableMetaData> load(final MetaDataConnectionAdapter connectionAdapter, final String tableNamePattern, final DatabaseType databaseType) throws SQLException {
        String formattedTableNamePattern = formatTableNamePattern(tableNamePattern, databaseType);
        return isTableExist(connectionAdapter, formattedTableNamePattern)
                ? Optional.of(new TableMetaData(formattedTableNamePattern, ColumnMetaDataLoader.load(
                        connectionAdapter, formattedTableNamePattern, databaseType), IndexMetaDataLoader.load(connectionAdapter, formattedTableNamePattern)))
                : Optional.empty();
    }
    
    private static String formatTableNamePattern(final String tableNamePattern, final DatabaseType databaseType) {
        return DatabaseMetaDataDialectHandlerFactory.findHandler(databaseType).map(handler -> handler.formatTableNamePattern(tableNamePattern)).orElse(tableNamePattern);
    }
    
    private static boolean isTableExist(final Connection connection, final String tableNamePattern) throws SQLException {
        try (ResultSet resultSet = connection.getMetaData().getTables(connection.getCatalog(), connection.getSchema(), tableNamePattern, null)) {
            return resultSet.next();
        }
    }
}
