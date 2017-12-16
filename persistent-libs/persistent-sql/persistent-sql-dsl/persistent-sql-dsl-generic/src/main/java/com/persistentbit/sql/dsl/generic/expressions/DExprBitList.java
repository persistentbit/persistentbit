package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.collections.PBitList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public interface DExprBitList extends DExpr<PBitList>{
	DExprBoolean eq(DExpr<PBitList> other);
	DExprBoolean notEq(DExpr<PBitList> other);

	DExprBoolean	isNull();
	DExprBoolean	isNotNull();

}
