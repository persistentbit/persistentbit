package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple7;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.tuples.Tuple7;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public class Tuple7TypeFactory implements ExprTypeFactory{

	private final ExprContext context;


	@Override
	public Class getTypeClass() {
		return ETuple7.class;
	}

	public Tuple7TypeFactory(ExprContext context) {
		this.context = context;
	}


	@Override
	public DExpr buildAlias(DExpr expr, String alias) {
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
	public DExpr buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DExpr onlyTableColumn(DExpr genExpr) {
		ETuple7 expr = (ETuple7) genExpr;
		DExpr   e1   = context.getTypeFactory(expr.v1()).onlyTableColumn(expr);
		DExpr   e2   = context.getTypeFactory(expr.v2()).onlyTableColumn(expr);
		DExpr   e3   = context.getTypeFactory(expr.v3()).onlyTableColumn(expr);
		DExpr   e4   = context.getTypeFactory(expr.v4()).onlyTableColumn(expr);
		DExpr   e5   = context.getTypeFactory(expr.v5()).onlyTableColumn(expr);
		DExpr   e6   = context.getTypeFactory(expr.v6()).onlyTableColumn(expr);
		DExpr   e7   = context.getTypeFactory(expr.v7()).onlyTableColumn(expr);
		return new ETuple7Impl(e1, e2, e3, e4, e5, e6, e7);
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
		ETuple7 expr = (ETuple7) genExpr;
		DExpr e1 = context.getTypeFactory(expr.v1()).buildSelection(expr.v1(),
																	prefixAlias == null ? null : prefixAlias + "t1_"
		);
		DExpr e2 = context.getTypeFactory(expr.v2()).buildSelection(expr.v2(),
																	prefixAlias == null ? null : prefixAlias + "t2_"
		);
		DExpr e3 = context.getTypeFactory(expr.v3()).buildSelection(expr.v3(),
																	prefixAlias == null ? null : prefixAlias + "t3_"
		);
		DExpr e4 = context.getTypeFactory(expr.v4()).buildSelection(expr.v4(),
																	prefixAlias == null ? null : prefixAlias + "t4_"
		);
		DExpr e5 = context.getTypeFactory(expr.v5()).buildSelection(expr.v5(),
																	prefixAlias == null ? null : prefixAlias + "t5_"
		);
		DExpr e6 = context.getTypeFactory(expr.v6()).buildSelection(expr.v6(),
																	prefixAlias == null ? null : prefixAlias + "t6_"
		);
		DExpr e7 = context.getTypeFactory(expr.v7()).buildSelection(expr.v7(),
																	prefixAlias == null ? null : prefixAlias + "t7_"
		);
		return new ETuple7Impl<>(e1, e2, e3, e4, e5, e6, e7);
	}

	@Override
	public PList<DExpr> expand(DExpr genExpr) {
		ETuple7 expr = (ETuple7) genExpr;
		return context.getTypeFactory(expr.v1()).expand(expr.v1())
			.plusAll(context.getTypeFactory(expr.v2()).expand(expr.v2()))
			.plusAll(context.getTypeFactory(expr.v3()).expand(expr.v3()))
			.plusAll(context.getTypeFactory(expr.v4()).expand(expr.v4()))
			.plusAll(context.getTypeFactory(expr.v5()).expand(expr.v5()))
			.plusAll(context.getTypeFactory(expr.v6()).expand(expr.v6()))
			.plusAll(context.getTypeFactory(expr.v7()).expand(expr.v7()))
			;
	}

	@Override
	public ExprTypeJdbcConvert getJdbcConverter(DExpr expr) {
		ETuple7Impl impl = (ETuple7Impl) expr;
		return impl.getJdbcConverter();
	}

	@Override
	public SqlWithParams toSql(DExpr genExpr) {
		ETuple7 expr = (ETuple7) genExpr;
		return context.getTypeFactory(expr.v1()).toSql(expr.v1())
			.add(", ").add(context.getTypeFactory(expr.v2()).toSql(expr.v2()))
			.add(", ").add(context.getTypeFactory(expr.v3()).toSql(expr.v3()))
			.add(", ").add(context.getTypeFactory(expr.v4()).toSql(expr.v4()))
			.add(", ").add(context.getTypeFactory(expr.v5()).toSql(expr.v5()))
			.add(", ").add(context.getTypeFactory(expr.v6()).toSql(expr.v6()))
			.add(", ").add(context.getTypeFactory(expr.v7()).toSql(expr.v7()))
			;
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		, E5 extends DExpr<J5>, J5
		, E6 extends DExpr<J6>, J6
		, E7 extends DExpr<J7>, J7
		>
	ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7> of(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5, E6 e6, E7 e7
	) {
		return new ETuple7Impl<>(e1, e2, e3, e4, e5, e6, e7);
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	private final class ETuple7Impl<
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		, E5 extends DExpr<J5>, J5
		, E6 extends DExpr<J6>, J6
		, E7 extends DExpr<J7>, J7

		> implements ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7>,
		ExprTypeImpl<ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7>, Tuple7<J1, J2, J3, J4, J5, J6, J7>>{

		private final E1 v1;
		private final E2 v2;
		private final E3 v3;
		private final E4 v4;
		private final E5 v5;
		private final E6 v6;
		private final E7 v7;

		private ETuple7Impl(E1 v1, E2 v2, E3 v3, E4 v4, E5 v5, E6 v6, E7 v7) {
			this.v1 = v1;
			this.v2 = v2;
			this.v3 = v3;
			this.v4 = v4;
			this.v5 = v5;
			this.v6 = v6;
			this.v7 = v7;

		}

		@Override
		public ExprTypeFactory getTypeFactory() {
			return Tuple7TypeFactory.this;
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

		@Override
		public E4 v4() {
			return v4;
		}

		@Override
		public E5 v5() {
			return v5;
		}

		@Override
		public E6 v6() {
			return v6;
		}

		@Override
		public E7 v7() {
			return v7;
		}

		public ExprTypeJdbcConvert getJdbcConverter() {
			return ExprTypeJdbcConvert.createMultiColumn(
				PList.val(
					context.getJdbcConverter(v1)
					, context.getJdbcConverter(v2)
					, context.getJdbcConverter(v3)
					, context.getJdbcConverter(v4)
					, context.getJdbcConverter(v5)
					, context.getJdbcConverter(v6)
					, context.getJdbcConverter(v7)
				)
				, ol ->
					ol == null || (ol[0] == null && ol[1] == null && ol[2] == null && ol[3] == null && ol[4] == null
						&& ol[5] == null && ol[6] == null)
						? null
						: Tuple7.of(ol[0], ol[1], ol[2], ol[3], ol[4], ol[5], ol[6])
			);
		}
	}
}
