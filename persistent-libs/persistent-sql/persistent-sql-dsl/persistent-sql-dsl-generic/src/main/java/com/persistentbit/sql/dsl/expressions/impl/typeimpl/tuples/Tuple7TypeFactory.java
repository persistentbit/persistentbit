package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple7;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.tuples.Tuple7;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public class Tuple7TypeFactory extends AbstractTupleTypeFactor{

	public Tuple7TypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected Tuple7TypeFactory.ETuple7Impl buildInstance(DExpr[] items) {
		return new Tuple7TypeFactory.ETuple7Impl(items, this, o -> {
			Object[] ol = (Object[]) o;
			return new Tuple7(ol[0], ol[1], ol[2], ol[3], ol[4], ol[5], ol[6]);
		});
	}

	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6, E7 extends DExpr<J7>, J7>
	ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7> of(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5, E6 e6,
																	   E7 e7) {
		return (ETuple7) buildInstance(new DExpr[]{e1, e2, e3, e4, e5, e6, e7});
	}

	static class ETuple7Impl<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6, E7 extends DExpr<J7>, J7>
		extends
		ETupleImpl<ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7>, Tuple7<J1, J2, J3, J4, J5, J6, J7>>
		implements ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7>{

		public ETuple7Impl(DExpr[] items,
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

		@Override
		public E5 v5() {
			return (E5) items[4];
		}

		@Override
		public E6 v6() {
			return (E6) items[5];
		}

		@Override
		public E7 v7() {
			return (E7) items[6];
		}
	}
}
