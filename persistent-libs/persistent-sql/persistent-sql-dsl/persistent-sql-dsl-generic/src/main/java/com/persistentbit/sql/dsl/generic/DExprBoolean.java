package com.persistentbit.sql.dsl.generic;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DExprBoolean extends DExpr<Boolean>{
	DExprBoolean not();
	DExprBoolean and(DExprBoolean other);
	DExprBoolean or(DExprBoolean other);
	DExprBoolean eq(DExprBoolean other);
}
