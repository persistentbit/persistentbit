package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple3;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.tuples.Tuple3;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public class Tuple3TypeFactory implements ExprTypeFactory{

	private final ExprContext context;


	@Override
	public Class getTypeClass() {
		return ETuple3.class;
	}

	public Tuple3TypeFactory(ExprContext context) {
		this.context = context;
	}


	@Override
	public DExpr buildAlias(String alias) {
		throw new UnsupportedOperationException("buildAlias on a ETuple");
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
	public DExpr buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DExpr onlyTableColumn(DExpr genExpr) {
		ETuple3 expr = (ETuple3) genExpr;
		DExpr   e1   = context.getTypeFactory(expr.v1()).onlyTableColumn(expr);
		DExpr   e2   = context.getTypeFactory(expr.v2()).onlyTableColumn(expr);
		DExpr   e3   = context.getTypeFactory(expr.v3()).onlyTableColumn(expr);
		return new Tuple3TypeFactory.ETuple3Impl(e1, e2, e3);
	}

	@Override
	public DExpr buildVal(Object value) {
		throw new UnsupportedOperationException("buildVal on ETuple");
	}


	@Override
	public DExpr buildParam(Function paramGetter) {
		throw new ToDo();
	}

	@Override
	public DExpr buildCall(String callName, Object... params) {
		throw new ToDo();
	}

	@Override
	public DExpr buildSelection(DExpr genExpr, String prefixAlias) {
		ETuple3 expr = (ETuple3) genExpr;
		DExpr e1 = context.getTypeFactory(expr.v1()).buildSelection(expr.v1(),
																	prefixAlias == null ? null : prefixAlias + "t1_"
		);
		DExpr e2 = context.getTypeFactory(expr.v2()).buildSelection(expr.v2(),
																	prefixAlias == null ? null : prefixAlias + "t2_"
		);
		DExpr e3 = context.getTypeFactory(expr.v3()).buildSelection(expr.v3(),
																	prefixAlias == null ? null : prefixAlias + "t3_"
		);
		return new Tuple3TypeFactory.ETuple3Impl<>(e1, e2, e3);
	}

	@Override
	public PList<DExpr> expand(DExpr genExpr) {
		ETuple3 expr = (ETuple3) genExpr;
		return context.getTypeFactory(expr.v1()).expand(expr.v1())
			.plusAll(context.getTypeFactory(expr.v2()).expand(expr.v2()))
			.plusAll(context.getTypeFactory(expr.v3()).expand(expr.v3()))
			;
	}

	@Override
	public ExprTypeJdbcConvert getJdbcConverter(DExpr expr) {
		Tuple3TypeFactory.ETuple3Impl impl = (Tuple3TypeFactory.ETuple3Impl) expr;
		return impl.getJdbcConverter();
	}

	@Override
	public SqlWithParams toSql(DExpr genExpr) {
		ETuple3 expr = (ETuple3) genExpr;
		return context.getTypeFactory(expr.v1()).toSql(expr.v1())
			.add(", ").add(context.getTypeFactory(expr.v2()).toSql(expr.v2()))
			.add(", ").add(context.getTypeFactory(expr.v3()).toSql(expr.v3()))
			;
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		>
	ETuple3<E1, J1, E2, J2, E3, J3> of(E1 e1, E2 e2, E3 e3) {
		return new Tuple3TypeFactory.ETuple3Impl<>(e1, e2, e3);
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	private final class ETuple3Impl<
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3

		> implements ETuple3<E1, J1, E2, J2, E3, J3>,
		ExprTypeImpl<ETuple3<E1, J1, E2, J2, E3, J3>, Tuple3<J1, J2, J3>>{

		private final E1 v1;
		private final E2 v2;
		private final E3 v3;


		private ETuple3Impl(E1 v1, E2 v2, E3 v3) {
			this.v1 = v1;
			this.v2 = v2;
			this.v3 = v3;


		}

		@Override
		public ExprTypeFactory getTypeFactory() {
			return Tuple3TypeFactory.this;
		}

		@Override
		public E1 v1() {
			return v1;
		}

		@Override
		public E2 v2() {
			return v2;
		}

		@Override
		public E3 v3() {
			return v3;
		}


		public ExprTypeJdbcConvert getJdbcConverter() {
			return ExprTypeJdbcConvert.createMultiColumn(
				PList.val(
					context.getJdbcConverter(v1)
					, context.getJdbcConverter(v2)
					, context.getJdbcConverter(v3)


				)
				, ol ->
					ol == null || (ol[0] == null && ol[1] == null && ol[2] == null)
						? null
						: Tuple3.of(ol[0], ol[1], ol[2])
			);
		}
	}
}
