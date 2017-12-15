package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public class DFloatBinOp extends DFloatAbstract{
	private final DExpr left;
	private final NumberBinOperator operator;
	private final DExpr right;

	public DFloatBinOp(DExpr left, NumberBinOperator operator, DExpr right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return operator.asSql(context,left,right);
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return _toSql(context).add(alias == null ? "" : " AS " + alias);
	}
}
