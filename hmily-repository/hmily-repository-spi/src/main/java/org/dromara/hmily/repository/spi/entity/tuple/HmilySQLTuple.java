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

package org.dromara.hmily.repository.spi.entity.tuple;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Hmily SQL tuple.
 *
 * @author zhaojun
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public final class HmilySQLTuple implements Serializable {
    
    private static final long serialVersionUID = -5978500621198003611L;
    
    private String tableName;
    
    private HmilySQLManipulation manipulationType;
    
    private List<Object> primaryKeyValues;
    
    private Map<String, Object> beforeImage;
    
    private Map<String, Object> afterImage;
}
