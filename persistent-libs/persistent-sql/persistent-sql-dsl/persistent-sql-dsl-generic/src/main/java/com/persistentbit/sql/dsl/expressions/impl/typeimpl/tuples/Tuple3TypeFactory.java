package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple3;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.tuples.Tuple3;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public class Tuple3TypeFactory extends AbstractTupleTypeFactor{

	public Tuple3TypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected Tuple3TypeFactory.ETuple3Impl buildInstance(DExpr[] items) {
		return new Tuple3TypeFactory.ETuple3Impl(items, this, o -> {
			Object[] ol = (Object[]) o;
			return new Tuple3(ol[0], ol[1], ol[2]);
		});
	}

	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3>
	ETuple3<E1, J1, E2, J2, E3, J3> of(E1 e1, E2 e2, E3 e3) {
		return (ETuple3) buildInstance(new DExpr[]{e1, e2, e3});
	}

	static class ETuple3Impl<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3>
		extends ETupleImpl<ETuple3<E1, J1, E2, J2, E3, J3>, Tuple3<J1, J2, J3>>
		implements ETuple3<E1, J1, E2, J2, E3, J3>{

		public ETuple3Impl(DExpr[] items,
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

	}
}
