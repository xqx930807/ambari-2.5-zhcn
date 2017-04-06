#!/usr/bin/env python
"""
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Ambari Agent

"""

from resource_management.core.resources.system import Execute
from resource_management.core.logger import Logger


class JcePolicyInfo:
  def __init__(self, java_exec, java_home):
    self.java_exec = java_exec
    self.java_home = java_home
    self.jar = "/var/lib/ambari-agent/tools/jcepolicyinfo.jar"

  def is_unlimited_key_jce_policy(self):
    Logger.info("Testing the JVM's JCE policy to see it if supports an unlimited key length.")

    try:
      Execute(self._command("-tu"),
              environment={'JAVA_HOME': self.java_home},
              logoutput=True
              )
      return True
    except Exception:
      return False


  def _command(self, options):
    return "{0} -jar {1} {2}".format(self.java_exec, self.jar, options)
