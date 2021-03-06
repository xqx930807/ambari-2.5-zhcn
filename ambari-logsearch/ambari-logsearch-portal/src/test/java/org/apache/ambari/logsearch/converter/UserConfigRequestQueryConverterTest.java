/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ambari.logsearch.converter;

import org.apache.ambari.logsearch.model.request.impl.UserConfigRequest;
import org.apache.solr.client.solrj.SolrQuery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserConfigRequestQueryConverterTest extends AbstractRequestConverterTest {

  private UserConfigRequestQueryConverter underTest;

  @Before
  public void setUp() {
    underTest = new UserConfigRequestQueryConverter();
  }

  @Test
  public void testConvert() {
    // GIVEN
    UserConfigRequest request = new UserConfigRequest();
    request.setRowType("myRowType"); // TODO: validate these 3 fields @Valid on UserConfigRequest object -> not null
    request.setFilterName("myFilterName");
    // WHEN
    SolrQuery queryResult = underTest.convert(request);
    // THEN
    assertEquals("?q=*%3A*&fq=rowtype%3AmyRowType&fq=filtername%3A*myFilterName*&start=0&rows=10&sort=filtername+asc",
      queryResult.toQueryString());
  }
}
