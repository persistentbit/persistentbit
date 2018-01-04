package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class BinOpTypeStrategy<J> extends AbstractTypeStrategy<J>{

	private final ExprContext   context;
	private final DExpr         left;
	private final BinOpOperator operator;
	private final DExpr         right;

	public BinOpTypeStrategy(ExprContext context,
							 DExpr left,
							 BinOpOperator operator,
							 DExpr right
	) {
		this.context = context;
		this.left = left;
		this.operator = operator;
		this.right = right;
	}



	@Override
	public SqlWithParams _toSql() {
		SqlWithParams sqlLeft  = context.toSql(left);
		SqlWithParams sqlRight = context.toSql(right);
		return context.getBinOpSqlBuilder(operator)
			.apply(left,sqlLeft,right,sqlRight);
	}

	@Override
	public String _createAliasName(String aliasPrefix) {
		return aliasPrefix;
	}

	@Override
	public TypeStrategy<J> onlyColumnName() {
		return this;
	}


}
