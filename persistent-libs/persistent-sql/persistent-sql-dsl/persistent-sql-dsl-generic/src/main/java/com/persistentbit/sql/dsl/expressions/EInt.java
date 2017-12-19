package com.persistentbit.sql.dsl.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public interface EInt extends DExpr<Integer>,
	ENumberMixin<EInt,EInt,EInt,ELong,EFloat,EDouble,EBigDecimal ,EInt,Integer>{


	static EInt from(DExpr<Integer> expr){
		return (EInt)expr;
	}

}
