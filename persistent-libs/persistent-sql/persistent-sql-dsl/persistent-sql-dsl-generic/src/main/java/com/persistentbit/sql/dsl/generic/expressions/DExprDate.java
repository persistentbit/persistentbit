package com.persistentbit.sql.dsl.generic.expressions;

import java.time.LocalDate;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DExprDate extends DExpr<LocalDate>{
	DExprBoolean eq(DExpr<LocalDate> other);
	DExprBoolean eq(LocalDate other);

	DExprBoolean notEq(DExpr<LocalDate> other);
	DExprBoolean notEq(LocalDate other);

	DExprBoolean lt(DExpr<LocalDate> other);
	DExprBoolean lt(LocalDate other);

	DExprBoolean gt(DExpr<LocalDate> other);
	DExprBoolean gt(LocalDate other);

	DExprBoolean ltEq(DExpr<LocalDate> other);
	DExprBoolean ltEq(LocalDate other);

	DExprBoolean gtEq(DExpr<LocalDate> other);
	DExprBoolean gtEq(LocalDate other);

	DExprBoolean isNull();
	DExprBoolean isNotNull();

}
