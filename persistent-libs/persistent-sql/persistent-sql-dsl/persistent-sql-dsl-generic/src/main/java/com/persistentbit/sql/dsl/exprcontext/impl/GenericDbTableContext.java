package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
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
	private final DbSqlContext	sqlContext;
	private final String fullTableName;
	@Nullable
	private final String alias;

	public GenericDbTableContext(DbSqlContext sqlContext, String fullTableName,@Nullable String alias) {
		this.sqlContext = Objects.requireNonNull(sqlContext);
		this.fullTableName = Objects.requireNonNull(fullTableName);
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
		return new DBooleanTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName));
	}

	@Override
	public DExprByte createExprByte(DTable table, String columnName
	) {
		return new DByteTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName));
	}

	@Override
	public DExprShort createExprShort(DTable table, String columnName
	) {
		return new DShortTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName));
	}

	@Override
	public DExprInt createExprInt(DTable table, String columnName
	) {
		return new DIntTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName));
	}

	@Override
	public DExprLong createExprLong(DTable table, String columnName
	) {
		return new DLongTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName));
	}

	@Override
	public DExprDouble createExprDouble(DTable table, String columnName
	) {
		return new DDoubleTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName));
	}

	@Override
	public DExprBigDecimal createExprBigDecimal(DTable table, String columnName
	) {
		return new DBigDecimalTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName));
	}

	@Override
	public DExprString createExprString(DTable table, String columnName
	) {
		return new DStringTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName));

	}

	@Override
	public DExprDateTime createExprDateTime(DTable table, String columnName
	) {
		return new DDateTimeTableFieldExpr(new GenericDbTableFieldExprContext(this, columnName));
	}

	@Override
	public DbSqlContext getSqlContext() {
		return sqlContext;
	}

	@Override
	public Query createQuery(DTable table
	) {
		return new QueryImpl(sqlContext, PList.val(table));
	}

	@Override
	public DbTableContext withAlias(String alias) {
		return new GenericDbTableContext(sqlContext,fullTableName,alias);
	}
}
