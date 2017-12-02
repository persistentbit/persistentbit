package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprBigDecimal;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DBigDecimalTableFieldExpr extends DBigDecimalAbstract{
	private final DbTableFieldExprContext context;

	public DBigDecimalTableFieldExpr(DbTableFieldExprContext context) {
		this.context = context;
	}
	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return SqlWithParams.sql(this.context._getFieldSelectionName(context));
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return _toSql(context).add(alias == null ? "" : " AS " + alias  +  "_" + this.context._getFieldName());
	}

	@Override
	public DExprBigDecimal _withAlias(String alias) {
		return alias == null ? this : super._withAlias(alias + "_" + context._getFieldName());
	}

}
