package com.persistentbit.sql.dsl.generic;

import com.persistentbit.collections.PList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DExprNumber<N extends Number> extends DExpr<N>{
	DExprBoolean eq(DExpr<Number> other);
	DExprBoolean eq(Number other);

	DExprBoolean notEq(DExpr<Number> other);
	DExprBoolean notEq(Number other);

	DExprBoolean lt(DExpr<Number> other);
	DExprBoolean lt(Number other);

	DExprBoolean gt(DExpr<Number> other);
	DExprBoolean gt(Number other);

	DExprBoolean ltEq(DExpr<Number> other);
	DExprBoolean ltEq(Number other);

	DExprBoolean gtEq(DExpr<Number> other);
	DExprBoolean gtEq(Number other);

	DExprBoolean isNull();
	DExprBoolean isNotNull();

	DExprBoolean in(PList<DExpr<Number>> values);
	DExprBoolean in(DExpr<Number>...values);


}
