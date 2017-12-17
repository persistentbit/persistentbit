package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.collections.PByteList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public interface EByteList extends DExpr<PByteList>{
	EBool eq(DExpr<PByteList> other);
	EBool notEq(DExpr<PByteList> other);

	EBool isNull();
	EBool isNotNull();
}
