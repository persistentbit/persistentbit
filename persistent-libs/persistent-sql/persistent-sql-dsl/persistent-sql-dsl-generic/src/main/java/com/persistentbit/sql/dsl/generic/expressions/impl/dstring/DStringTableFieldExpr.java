package com.persistentbit.sql.dsl.generic.expressions.impl.dstring;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DStringTableFieldExpr extends DStringAbstract{
	private final DbTableFieldExprContext context;

	public DStringTableFieldExpr(DbTableFieldExprContext context) {
		this.context = context;
	}

	@Override
	public SqlWithParams toSql(DbSqlContext context) {
		return new SqlWithParams(this.context._getFieldSelectionName(context));
	}
	@Override
	public SqlWithParams toSqlSelection(DbSqlContext sqlContext) {
		SqlWithParams inside = toSql(sqlContext);
		return inside;
	}

	@Override
	public DExprString withSelectionAlias(String alias) {
		return alias == null ? this : super.withSelectionAlias(alias + "_" + context._getFieldName());
	}

}
