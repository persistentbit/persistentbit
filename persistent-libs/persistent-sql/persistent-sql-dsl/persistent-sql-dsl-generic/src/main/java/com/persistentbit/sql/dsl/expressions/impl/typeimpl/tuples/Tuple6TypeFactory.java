package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple6;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.tuples.Tuple6;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public class Tuple6TypeFactory extends AbstractTupleTypeFactor{

	public Tuple6TypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected Tuple6TypeFactory.ETuple6Impl buildInstance(DExpr[] items) {
		return new Tuple6TypeFactory.ETuple6Impl(items, this, o -> {
			Object[] ol = (Object[]) o;
			return new Tuple6(ol[0], ol[1], ol[2], ol[3], ol[4], ol[5]);
		});
	}

	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6>
	ETuple6<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6> of(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5, E6 e6) {
		return (ETuple6) buildInstance(new DExpr[]{e1, e2, e3, e4, e5, e6});
	}

	static class ETuple6Impl<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6>
		extends ETupleImpl<ETuple6<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6>, Tuple6<J1, J2, J3, J4, J5, J6>>
		implements ETuple6<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6>{

		public ETuple6Impl(DExpr[] items,
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


	}
}
