package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.collections.PBitList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public interface EBitList extends DExpr<PBitList>{
	EBool eq(DExpr<PBitList> other);
	EBool notEq(DExpr<PBitList> other);

	EBool isNull();
	EBool isNotNull();

}
