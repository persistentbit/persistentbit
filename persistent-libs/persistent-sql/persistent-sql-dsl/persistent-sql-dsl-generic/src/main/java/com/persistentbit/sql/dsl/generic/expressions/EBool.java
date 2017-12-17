package com.persistentbit.sql.dsl.generic.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface EBool extends DExpr<Boolean>{

	EBool not();
	EBool and(DExpr<Boolean> other);
	EBool or(DExpr<Boolean> other);

	EBool eq(DExpr<Boolean> other);
	EBool notEq(DExpr<Boolean> other);

	EBool isNull();
	EBool isNotNull();

	<E2 extends DExpr<J2>,J2> ETuple2<EBool,E2,Boolean, J2> tuple2(E2 v2);
}
