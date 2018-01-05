package com.persistentbit.sql.dsl.expressions;

import com.persistentbit.collections.ImmutableArray;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public interface EArray<E1 extends DExpr<J1>, J1>
	extends DExpr<ImmutableArray<J1>>, EComparableMixIn<EArray<E1, J1>, ImmutableArray<J1>>{

	EArray<E1, J1> slice(EInt start, EInt end);

	EArray<E1, J1> slice(int start, int end);

	E1 get(EInt index);

	E1 get(int index);

}
