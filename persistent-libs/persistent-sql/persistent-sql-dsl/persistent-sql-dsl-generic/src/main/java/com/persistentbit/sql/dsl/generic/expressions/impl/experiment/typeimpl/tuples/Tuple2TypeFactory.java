package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.tuples;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.ETuple2;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.BinOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.SingleOpOperator;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class Tuple2TypeFactory<E1 extends DExpr<J1>,E2 extends DExpr<J2>,J1,J2> implements ExprTypeFactory<ETuple2<E1,E2,J1,J2>, Tuple2<? extends J1,? extends J2>> {

	private final ExprContext context;
	private final Class<E1> typeClass1;
	private final Class<E2> typeClass2;

	@Override
	public Class getTypeClass() {
		return ETuple2.class;
	}

	public Tuple2TypeFactory(ExprContext context, Class<E1> typeClass1, Class<E2> typeClass2) {
		this.context = context;
		this.typeClass1 = typeClass1;
		this.typeClass2 = typeClass2;
	}

	@Override
	public <V extends Tuple2<? extends J1, ? extends J2>> ETuple2<E1, E2, J1, J2> buildVal(V value) {
		E1 e1 = context.getTypeFactory(typeClass1).buildVal(value._1);
		E2 e2 = context.getTypeFactory(typeClass2).buildVal(value._2);
		return new ETuple2Impl<>(e1,e2);
	}

	@Override
	public ETuple2<E1, E2, J1, J2> buildAlias(String alias) {
		E1 e1 = context.getTypeFactory(typeClass1).buildAlias(alias + "_t1");
		E2 e2 = context.getTypeFactory(typeClass2).buildAlias(alias + "_t2");
		return new ETuple2Impl<>(e1,e2);
	}

	@Override
	public ETuple2<E1, E2, J1, J2> buildBinOp(DExpr left, BinOpOperator op, DExpr right
	) {
		throw new UnsupportedOperationException("BinOp " + op);
	}

	@Override
	public ETuple2<E1, E2, J1, J2> buildSingleOp(DExpr expr, SingleOpOperator op
	) {
		throw new UnsupportedOperationException("SingleOp " + op);
	}

	@Override
	public ETuple2<E1, E2, J1, J2> buildTableField(String fieldSelectionName, String fieldName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ETuple2<E1, E2, J1, J2> buildSelection(ETuple2<E1, E2, J1, J2> expr, String prefixAlias) {
		E1 e1 = context.getTypeFactory(typeClass1).buildSelection(expr.v1(),prefixAlias + "_t1");
		E2 e2 = context.getTypeFactory(typeClass2).buildSelection(expr.v2(),prefixAlias + "_t2");
		return new ETuple2Impl<>(e1,e2);
	}

	@Override
	public PList<DExpr> expand(ETuple2<E1, E2, J1, J2> expr) {
		return context.getTypeFactory(typeClass1).expand(expr.v1())
			.plusAll(context.getTypeFactory(typeClass2).expand(expr.v2()));
	}

	@Override
	public SqlWithParams toSql(ETuple2<E1, E2, J1, J2> expr) {
		return context.getTypeFactory(typeClass1).toSql(expr.v1())
			.add(", ")
			.add(context.getTypeFactory(typeClass2).toSql(expr.v2()));
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	private class ETuple2Impl<E1 extends DExpr<J1>,E2 extends DExpr<J2>,J1,J2> implements ETuple2<E1,E2,J1,J2>{

		private final E1 v1;
		private final E2 v2;

		public ETuple2Impl(E1 v1, E2 v2) {
			this.v1 = v1;
			this.v2 = v2;
		}

		@Override
		public E1 v1() {
			return v1;
		}

		@Override
		public E2 v2() {
			return v2;
		}
	}
}
