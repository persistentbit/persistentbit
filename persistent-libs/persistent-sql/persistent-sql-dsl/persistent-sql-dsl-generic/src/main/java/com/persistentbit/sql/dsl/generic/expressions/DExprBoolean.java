package com.persistentbit.sql.dsl.generic.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DExprBoolean extends DExpr<Boolean>{

	DExprBoolean not();
	DExprBoolean and(DExpr<Boolean> other);
	DExprBoolean or(DExpr<Boolean> other);

	DExprBoolean eq(DExpr<Boolean> other);
	DExprBoolean notEq(DExpr<Boolean> other);

	DExprBoolean	isNull();
	DExprBoolean	isNotNull();

	@Override
	DExprBoolean withSelectionAlias(String alias);
}
