/*
 *  Copyright 2015 Niek Haarman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.nhaarman.sqlitebuilder.integration;

import com.nhaarman.sqlitebuilder.FinishedStatement;
import org.junit.Test;

import static com.nhaarman.sqlitebuilder.impl.Statements.end;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("HardCodedStringLiteral")
public class EndTransactionTest extends IntegrationTestBase {

  @Test
  public void endTransaction() {
    /* When */
    FinishedStatement statement = end().transaction();

    /* Then */
    assertThat(toSql(statement), is("END TRANSACTION"));
  }
}
