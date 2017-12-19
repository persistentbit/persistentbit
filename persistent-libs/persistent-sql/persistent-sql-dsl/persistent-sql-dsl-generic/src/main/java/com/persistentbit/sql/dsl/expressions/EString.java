package com.persistentbit.sql.dsl.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface EString extends DExpr<String>, EComparableMixIn<EString,String>{

	EString concat(DExpr<String> other);
	EString concat(String other);


	EBool like(DExpr<String> likeOther);
	EBool like(String likeOther);



}
