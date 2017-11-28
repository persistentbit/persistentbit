package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.collections.PList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DExprNumber<N extends Number> extends DExpr<N>{
	DExprBoolean eq(DExpr<? extends Number> other);
	DExprBoolean eq(N other);

	DExprBoolean notEq(DExpr<? extends Number> other);
	DExprBoolean notEq(N other);

	DExprBoolean lt(DExpr<? extends Number> other);
	DExprBoolean lt(N other);

	DExprBoolean gt(DExpr<? extends Number> other);
	DExprBoolean gt(N other);

	DExprBoolean ltEq(DExpr<? extends Number> other);
	DExprBoolean ltEq(N other);

	DExprBoolean gtEq(DExpr<? extends Number> other);
	DExprBoolean gtEq(N other);

	DExprBoolean isNull();
	DExprBoolean isNotNull();

	DExprBoolean in(PList<DExpr<? extends Number>> values);
	DExprBoolean in(DExpr<? extends Number>...values);


}
