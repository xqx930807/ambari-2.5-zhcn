/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.server.stack;

import org.apache.ambari.server.state.theme.Theme;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ThemeModuleTest {


  @Test
  public void testResolve() throws Exception {
    File parentThemeFile = new File(this.getClass().getClassLoader().getResource("parent-theme.json").getFile());
    File childThemeFile = new File(this.getClass().getClassLoader().getResource("child-theme.json").getFile());

    ThemeModule parentModule = new ThemeModule(parentThemeFile);
    ThemeModule childModule = new ThemeModule(childThemeFile);

    childModule.resolve(parentModule, null, null, null);

    Theme childTheme = childModule.getModuleInfo().getThemeMap().get(ThemeModule.THEME_KEY);
    Theme parentTheme = parentModule.getModuleInfo().getThemeMap().get(ThemeModule.THEME_KEY);

    assertNotNull(childTheme.getThemeConfiguration().getLayouts()); // not defined in child should be inherited

    assertEquals(10, parentTheme.getThemeConfiguration().getPlacement().getConfigs().size());
    assertEquals(12, childTheme.getThemeConfiguration().getPlacement().getConfigs().size()); //two more inherited

    assertEquals(10, parentTheme.getThemeConfiguration().getWidgets().size());
    assertEquals(12, childTheme.getThemeConfiguration().getWidgets().size());


  }
}