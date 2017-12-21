package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple2;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class Tuple2TypeFactory implements ExprTypeFactory {

	private final ExprContext context;


	@Override
	public Class getTypeClass() {
		return ETuple2.class;
	}

	public Tuple2TypeFactory(ExprContext context) {
		this.context = context;
	}



	@Override
	public DExpr buildAlias(String alias) {
		throw new UnsupportedOperationException("buildAlias on a ETuple2");
	}

	@Override
	public DExpr buildBinOp(DExpr left, BinOpOperator op, DExpr right
	) {
		throw new UnsupportedOperationException("BinOp " + op);
	}

	@Override
	public DExpr buildSingleOp(DExpr expr, SingleOpOperator op
	) {
		throw new UnsupportedOperationException("SingleOp " + op);
	}

	@Override
	public DExpr buildTableField(String fieldSelectionName, String fieldName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DExpr buildVal(Object value) {
		throw new UnsupportedOperationException("buildVal on ETuple2");
	}



	@Override
	public DExpr buildParam(Function paramGetter) {
		throw new ToDo();
	}

	@Override
	public DExpr buildCall(String callName, DExpr[] params) {
		throw new ToDo();
	}

	@Override
	public DExpr buildSelection(DExpr genExpr, String prefixAlias) {
		ETuple2 expr = (ETuple2)genExpr;
		DExpr e1 = context.getTypeFactory(expr.v1()).buildSelection(expr.v1(),
			prefixAlias == null ? null : prefixAlias + "t1_");
		DExpr e2 = context.getTypeFactory(expr.v2()).buildSelection(expr.v2(),
			prefixAlias == null ? null : prefixAlias + "t2_");
		return new ETuple2Impl<>(e1,e2);
	}

	@Override
	public PList<DExpr> expand(DExpr genExpr) {
		ETuple2 expr = (ETuple2)genExpr;
		return context.getTypeFactory(expr.v1()).expand(expr.v1())
					  .plusAll(context.getTypeFactory(expr.v2()).expand(expr.v2()));
	}


	@Override
	public SqlWithParams toSql(DExpr genExpr) {
		ETuple2 expr = (ETuple2)genExpr;
		return context.getTypeFactory(expr.v1()).toSql(expr.v1())
			.add(", ")
			.add(context.getTypeFactory(expr.v2()).toSql(expr.v2()));
	}

	public <
			   E1 extends DExpr<J1>, J1,
			   E2 extends DExpr<J2>, J2
			   >
	ETuple2<E1,J1,E2,J2> of(E1 e1, E2 e2){
		return new ETuple2Impl<>(e1,e2);
	}
	@Override
	public ExprContext getExprContext() {
		return context;
	}

	private final class ETuple2Impl<E1 extends DExpr<J1>,E2 extends DExpr<J2>,J1,J2> implements ETuple2<E1,J1,E2,J2>,
																								ExprTypeImpl<ETuple2<E1,J1,E2,J2>,Tuple2<J1,J2>>{

		private final E1 v1;
		private final E2 v2;

		private ETuple2Impl(E1 v1, E2 v2) {
			this.v1 = v1;
			this.v2 = v2;
		}

		@Override
		public ExprTypeFactory getTypeFactory() {
			return Tuple2TypeFactory.this;
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
