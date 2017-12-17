package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.SingleOpOperator;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class SingleOpTypeStrategy<T> extends AbstractTypeStrategy<T>{
	private final DExpr expr;
	private final SingleOpOperator operator;

	public SingleOpTypeStrategy(Class<? extends DExpr<T>> typeClass,
								ExprTypeFactory exprTypeFactory,
								DExpr expr,
								SingleOpOperator operator
	) {
		super(typeClass, exprTypeFactory);
		this.expr = expr;
		this.operator = operator;
	}


	@Override
	public SqlWithParams _toSql() {
		SqlWithParams sql = getTypeStrategy(expr)._toSql();
		return getExprContext().getSingleOpSqlBuilder(operator)
			.apply(expr,sql);
	}
}
