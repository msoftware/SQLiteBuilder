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

package com.nhaarman.sqlitebuilder.impl;

import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.StatementExecutor;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class BaseFinishedStatementTest {

  @Test
  public void iterator_returnsProperIterator() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    TestBaseFinishedStatement statement = new TestBaseFinishedStatement(sqlPart);

    /* When */
    Iterator<SqlPart> iterator = statement.iterator();

    /* Then */
    assertThat(iterator.hasNext(), is(true));
    assertThat(iterator.next(), is((SqlPart) statement));
    assertThat(iterator.hasNext(), is(true));
    assertThat(iterator.next(), is(sqlPart));
    assertThat(iterator.hasNext(), is(false));
  }

  @Test
  public void executeOn_delegatesToParameter() {
    /* Given */
    StatementExecutor<?> statementExecutor = mock(StatementExecutor.class);
    TestBaseFinishedStatement statement = new TestBaseFinishedStatement(mock(SqlPart.class));

    /* When */
    statement.executeOn(statementExecutor);

    /* Then */
    verify(statementExecutor).execute(statement);
  }

  private static class TestBaseFinishedStatement extends BaseFinishedStatement {

    @NotNull
    private final SqlPart mPrevious;

    TestBaseFinishedStatement(@NotNull final SqlPart previous) {
      mPrevious = previous;
    }

    @Override
    public void prependTo(@NotNull final RawSqlBuilder builder) {

    }

    @Nullable
    @Override
    public SqlPart previous() {
      return mPrevious;
    }
  }
}