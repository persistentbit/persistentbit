package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple2;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.tuples.Tuple2;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class Tuple2TypeFactory extends AbstractTupleTypeFactor{

	public Tuple2TypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected ETuple2Impl buildInstance(DExpr[] items) {
		return new ETuple2Impl(items, this, o -> {
			Object[] ol = (Object[]) o;
			return new Tuple2(ol[0], ol[1]);
		});
	}
	public <
		E1 extends DExpr<J1>, J1,
		E2 extends DExpr<J2>, J2
		>
	ETuple2<E1, J1, E2, J2> of(E1 e1, E2 e2) {
		return (ETuple2) buildInstance(new DExpr[]{e1, e2});
	}

	static class ETuple2Impl<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2>
		extends ETupleImpl<ETuple2<E1, J1, E2, J2>, Tuple2<J1, J2>>
		implements ETuple2<E1, J1, E2, J2>{

		public ETuple2Impl(DExpr[] items,
						   AbstractTupleTypeFactor exprTypeFactory,
						   Function<Object[], Object> toJavaType) {
			super(items, exprTypeFactory, toJavaType);
		}


		@Override
		public E1 v1() {
			return (E1) items[0];
		}

		@Override
		public E2 v2() {
			return (E2) items[1];
		}
	}
}
