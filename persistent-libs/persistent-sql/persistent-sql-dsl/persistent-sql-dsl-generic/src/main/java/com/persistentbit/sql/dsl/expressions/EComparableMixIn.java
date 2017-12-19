package com.persistentbit.sql.dsl.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface EComparableMixIn<E extends DExpr<J>,J>{
	EBool eq(E other);
	EBool eq(J other);

	EBool notEq(E other);
	EBool notEq(J other);

	EBool lt(E other);
	EBool lt(J other);

	EBool gt(E other);
	EBool gt(J other);

	EBool ltEq(E other);
	EBool ltEq(J other);

	EBool gtEq(E other);
	EBool gtEq(J other);

	EBool isNull();
	EBool isNotNull();
}
