package com.persistentbit.sql.dsl.generic.expressions.impl.dbytelist;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprByteList;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class DByteListTableFieldExpr extends DByteListAbstract{
	private final DbTableFieldExprContext context;

	public DByteListTableFieldExpr(DbTableFieldExprContext context) {
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
	public DExprByteList _withAlias(String alias) {
		return alias == null ? this : super._withAlias(alias + "_" + context._getFieldName());
	}

	@Override
	public String _getColumnName() {
		return context._getFieldName();
	}

}
