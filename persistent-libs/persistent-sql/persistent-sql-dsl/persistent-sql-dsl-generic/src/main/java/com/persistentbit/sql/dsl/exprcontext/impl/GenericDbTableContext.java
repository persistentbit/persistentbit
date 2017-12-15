package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.date.DDateTableFieldExpr;
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
	public DExprBoolean createExprBoolean(String columnName) {
		return new DBooleanTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public DExprByte createExprByte(String columnName) {
		return new DByteTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public DExprShort createExprShort(String columnName) {
		return new DShortTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public DExprInt createExprInt(String columnName) {
		return new DIntTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public DExprLong createExprLong(String columnName
	) {
		return new DLongTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public DExprDouble createExprDouble(String columnName
	) {
		return new DDoubleTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public DExprBigDecimal createExprBigDecimal(String columnName
	) {
		return new DBigDecimalTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public DExprString createExprString(String columnName
	) {
		return new DStringTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));

	}

	@Override
	public DExprDateTime createExprDateTime(String columnName
	) {
		return new DDateTimeTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	public DExprDate createExprDate(String columnName){
		return new DDateTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(),columnName));
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
