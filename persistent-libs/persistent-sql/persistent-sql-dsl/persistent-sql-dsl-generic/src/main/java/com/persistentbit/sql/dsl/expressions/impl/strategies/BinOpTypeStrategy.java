package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class BinOpTypeStrategy<J> extends AbstractTypeStrategy<J>{
	private final DExpr left;
	private final BinOpOperator operator;
	private final DExpr right;
	public BinOpTypeStrategy(Class<? extends DExpr<J>> typeClass,
							 ExprTypeFactory exprTypeFactory,
							 DExpr left,
							 BinOpOperator operator,
							 DExpr right
	) {
		super(typeClass, exprTypeFactory);
		this.left = left;
		this.operator = operator;
		this.right = right;
	}



	@Override
	public SqlWithParams _toSql() {
		SqlWithParams sqlLeft = getTypeStrategy(left)._toSql();
		SqlWithParams sqlRight = getTypeStrategy(right)._toSql();
		return getExprContext().getBinOpSqlBuilder(operator)
			.apply(left,sqlLeft,right,sqlRight);
	}

}