package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.datetime.DDateTimeTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.dnumber.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.dstring.DStringTableFieldExpr;
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
	private final String fullTableName;
	private final String tableAlias;
	@Nullable
	private final String alias;

	public GenericDbTableContext(DbContext dbContext, String fullTableName,@Nullable String tableAlias, @Nullable String alias
	) {
		this.dbContext = Objects.requireNonNull(dbContext);
		this.fullTableName = Objects.requireNonNull(fullTableName);
		this.tableAlias = tableAlias;
		this.alias = alias;
	}


	@Override
	public String getTableName() {
		return fullTableName;
	}


	@Override
	public Optional<String> getAlias() {
		return Optional.ofNullable(alias);
	}

	@Override
	public DExprBoolean createExprBoolean(DTable table, String columnName
	) {
		return new DBooleanTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName,alias))._withAlias(alias);
	}

	@Override
	public DExprByte createExprByte(DTable table, String columnName
	) {
		return new DByteTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName,alias))._withAlias(alias);
	}

	@Override
	public DExprShort createExprShort(DTable table, String columnName
	) {
		return new DShortTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName,alias))._withAlias(alias);
	}

	@Override
	public DExprInt createExprInt(DTable table, String columnName
	) {
		return new DIntTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName,alias))._withAlias(alias);
	}

	@Override
	public DExprLong createExprLong(DTable table, String columnName
	) {
		return new DLongTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName,alias))._withAlias(alias);
	}

	@Override
	public DExprDouble createExprDouble(DTable table, String columnName
	) {
		return new DDoubleTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName,alias))._withAlias(alias);
	}

	@Override
	public DExprBigDecimal createExprBigDecimal(DTable table, String columnName
	) {
		return new DBigDecimalTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName,alias))._withAlias(alias);
	}

	@Override
	public DExprString createExprString(DTable table, String columnName
	) {
		return new DStringTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName,alias))._withAlias(alias);

	}

	@Override
	public DExprDateTime createExprDateTime(DTable table, String columnName
	) {
		return new DDateTimeTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName,alias))._withAlias(alias);
	}


	@Override
	public Query createQuery(DTable table
	) {
		return new QueryImpl(dbContext, PList.val(table));
	}

	@Override
	public DbTableContext withAlias(String alias) {
		return new GenericDbTableContext(dbContext,fullTableName,  tableAlias,alias);
	}


	@Override
	public DbTableContext withTableAlias(String tableAlias) {
		return new GenericDbTableContext(dbContext,fullTableName,tableAlias,alias);
	}

	@Override
	public Optional<String> getTableAlias() {
		return Optional.ofNullable(tableAlias);
	}
}
