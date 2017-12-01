package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprDouble;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DDoubleTableFieldExpr extends DDoubleAbstract{
	private DbTableFieldExprContext context;

	public DDoubleTableFieldExpr(DbTableFieldExprContext context) {
		this.context = context;
	}
	@Override
	public SqlWithParams toSql(DbSqlContext context) {
		return new SqlWithParams(this.context._getFieldSelectionName(context));
	}
	@Override
	public DExprDouble withSelectionAlias(String alias) {
		return alias == null ? this : super.withSelectionAlias(alias + "_" + context._getFieldName());
	}

}
