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

import com.nhaarman.sqlitebuilder.GroupBy;
import com.nhaarman.sqlitebuilder.Limit;
import com.nhaarman.sqlitebuilder.OrderBy;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SelectFrom;
import com.nhaarman.sqlitebuilder.SelectWhere;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class SelectFromImpl extends BaseFinishedStatement implements SelectFrom {

  @NotNull
  private final String mTableName;

  @NotNull
  private final SqlPart mPrevious;

  SelectFromImpl(@NotNull final String tableName, @NotNull final SqlPart previous) {
    mTableName = tableName;
    mPrevious = previous;
  }

  @NotNull
  @Override
  public SelectWhere where(@NotNull final String expression, @NotNull final Object... arguments) {
    return new WhereImpl(expression, arguments, this);
  }

  @NotNull
  @Override
  public GroupBy groupBy(@NotNull final String expression) {
    return new GroupByImpl(expression, this);
  }

  @NotNull
  @Override
  public OrderBy orderBy(@NotNull final String... terms) {
    return new OrderByImpl(terms, this);
  }

  @NotNull
  @Override
  public Limit limit(final long limit) {
    return new LimitImpl(limit, this);
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    builder.prepend(mTableName).prepend("FROM ");
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return mPrevious;
  }
}
