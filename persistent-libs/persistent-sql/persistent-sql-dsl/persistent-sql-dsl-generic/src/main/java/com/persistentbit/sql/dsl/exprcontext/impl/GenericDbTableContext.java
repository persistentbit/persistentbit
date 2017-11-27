package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.datetime.DDateTimeTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.dnumber.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.dstring.DStringTableFieldExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class GenericDbTableContext implements DbTableContext{
	private final DbSqlContext	sqlContext;
	private final String fullTableName;

	public GenericDbTableContext(DbSqlContext sqlContext, String fullTableName) {
		this.sqlContext = sqlContext;
		this.fullTableName = fullTableName;
	}

	@Override
	public String getTableName() {
		return fullTableName;
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
}
