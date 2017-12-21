package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/12/17
 */
public class FunctionCallTypeStrategy<J> extends AbstractTypeStrategy<J>{
	private final String callName;
	private final DExpr[] arguments;

	public FunctionCallTypeStrategy(Class<? extends DExpr<J>> typeClass,
									ExprTypeFactory exprTypeFactory,
									String callName,
									DExpr[] arguments) {
		super(typeClass, exprTypeFactory);
		this.callName = callName;
		this.arguments = arguments;
	}

	@Override
	public SqlWithParams _toSql() {
		SqlWithParams sql = SqlWithParams.sql(callName + "(");
		boolean first = true;
		for(DExpr expr : arguments){
			if(first == false){
				sql = sql.add(", ");
			}
			first = false;
			sql = sql.add(getExprContext().toSql(expr));
		}
		return sql.add(")");
	}


}
