package com.persistentbit.sql.dsl.generic.expressions;

import java.time.LocalTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DExprTime extends DExpr<LocalTime>{
		DExprBoolean eq(DExpr<LocalTime> other);
		DExprBoolean eq(LocalTime other);

		DExprBoolean notEq(DExpr<LocalTime> other);
		DExprBoolean notEq(LocalTime other);

		DExprBoolean lt(DExpr<LocalTime> other);
		DExprBoolean lt(LocalTime other);

		DExprBoolean gt(DExpr<LocalTime> other);
		DExprBoolean gt(LocalTime other);

		DExprBoolean ltEq(DExpr<LocalTime> other);
		DExprBoolean ltEq(LocalTime other);

		DExprBoolean gtEq(DExpr<LocalTime> other);
		DExprBoolean gtEq(LocalTime other);

		DExprBoolean isNull();
		DExprBoolean isNotNull();
}
