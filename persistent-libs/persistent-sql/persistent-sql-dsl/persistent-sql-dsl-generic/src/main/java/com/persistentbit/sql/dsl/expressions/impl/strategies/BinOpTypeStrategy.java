package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class BinOpTypeStrategy<J> extends AbstractTypeStrategy<J>{

	private final DExpr         left;
	private final BinOpOperator operator;
	private final DExpr         right;

	public BinOpTypeStrategy(DExpr left,
							 BinOpOperator operator,
							 DExpr right
	) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}



	@Override
	public SqlWithParams _toSql(AbstractTypeImpl impl) {
		ExprContext   context  = impl.getContext();
		SqlWithParams sqlLeft  = context.toSql(left);
		SqlWithParams sqlRight = context.toSql(right);
		return context.getBinOpSqlBuilder(operator)
			.apply(left,sqlLeft,right,sqlRight);
	}

	@Override
	public String _createAliasName(AbstractTypeImpl impl, String aliasPrefix) {
		return aliasPrefix;
	}

	@Override
	public TypeStrategy<J> onlyColumnName(AbstractTypeImpl impl) {
		return this;
	}


}
