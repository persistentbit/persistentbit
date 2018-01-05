package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple4;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.tuples.Tuple4;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public class Tuple4TypeFactory extends AbstractTupleTypeFactor{

	public Tuple4TypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected Tuple4TypeFactory.ETuple4Impl buildInstance(DExpr[] items) {
		return new Tuple4TypeFactory.ETuple4Impl(items, this, o -> {
			Object[] ol = (Object[]) o;
			return new Tuple4(ol[0], ol[1], ol[2], ol[3]);
		});
	}

	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4>
	ETuple4<E1, J1, E2, J2, E3, J3, E4, J4> of(E1 e1, E2 e2, E3 e3, E4 e4) {
		return (ETuple4) buildInstance(new DExpr[]{e1, e2, e3, e4});
	}

	static class ETuple4Impl<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4>
		extends ETupleImpl<ETuple4<E1, J1, E2, J2, E3, J3, E4, J4>, Tuple4<J1, J2, J3, J4>>
		implements ETuple4<E1, J1, E2, J2, E3, J3, E4, J4>{

		public ETuple4Impl(DExpr[] items,
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

		@Override
		public E3 v3() {
			return (E3) items[2];
		}

		@Override
		public E4 v4() {
			return (E4) items[3];
		}


	}
}
