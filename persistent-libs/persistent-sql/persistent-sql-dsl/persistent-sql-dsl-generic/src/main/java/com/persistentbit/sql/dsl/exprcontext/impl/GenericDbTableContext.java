package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.dsl.generic.query.impl.QueryImpl;

import java.util.Objects;
import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class GenericDbTableContext implements DbTableContext{

	private final DbContext dbContext;
	private final String    fullTableName;
	@Nullable
	private final String    tableAlias;

	public GenericDbTableContext(DbContext dbContext, String fullTableName, @Nullable String tableAlias) {
		this.dbContext = Objects.requireNonNull(dbContext);
		this.fullTableName = Objects.requireNonNull(fullTableName);
		this.tableAlias = tableAlias;
	}

	@Override
	public DbContext getDbContext() {
		return dbContext;
	}

	@Override
	public String getTableName() {
		return fullTableName;
	}

	private String getFieldTable() {
		return tableAlias != null ? tableAlias : fullTableName;
	}



	@Override
	public Query createQuery(DExprTable table) {
		return new QueryImpl(dbContext, PList.val(table));
	}

	@Override
	public DbTableContext withTableAlias(String tableAlias) {
		return new GenericDbTableContext(dbContext, fullTableName, tableAlias);
	}

	@Override
	public Optional<String> getTableAlias() {
		return Optional.ofNullable(tableAlias);
	}
}
