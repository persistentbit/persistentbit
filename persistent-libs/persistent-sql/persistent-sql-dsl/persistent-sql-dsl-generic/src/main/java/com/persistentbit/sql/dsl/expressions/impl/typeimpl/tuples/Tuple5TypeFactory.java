package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple5;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.tuples.Tuple5;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public class Tuple5TypeFactory extends AbstractTupleTypeFactor{

	public Tuple5TypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected Tuple5TypeFactory.ETuple5Impl buildInstance(DExpr[] items) {
		return new Tuple5TypeFactory.ETuple5Impl(items, this, o -> {
			Object[] ol = (Object[]) o;
			return new Tuple5(ol[0], ol[1], ol[2], ol[3], ol[4]);
		});
	}

	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5>
	ETuple5<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5> of(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5) {
		return (ETuple5) buildInstance(new DExpr[]{e1, e2, e3, e4, e5});
	}

	static class ETuple5Impl<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5>
		extends ETupleImpl<ETuple5<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5>, Tuple5<J1, J2, J3, J4, J5>>
		implements ETuple5<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5>{

		public ETuple5Impl(DExpr[] items,
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


	}
}
