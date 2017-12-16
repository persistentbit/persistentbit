package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.collections.PByteList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public interface DExprByteList extends DExpr<PByteList>{
	DExprBoolean eq(DExpr<PByteList> other);
	DExprBoolean notEq(DExpr<PByteList> other);

	DExprBoolean	isNull();
	DExprBoolean	isNotNull();
}
