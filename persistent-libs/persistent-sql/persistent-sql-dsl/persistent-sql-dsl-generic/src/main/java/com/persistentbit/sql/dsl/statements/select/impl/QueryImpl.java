package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.select.Join;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.TypedSelection1;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.tuples.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class QueryImpl implements Query{

	protected final QueryCtx qc;

	public QueryImpl(QueryCtx qc) {
		this.qc = qc;
	}

	public QueryImpl(ExprContext context, PList<Table> from) {
		this(QueryCtx.build(b -> b
			.setContext(context)
			.setFrom(from)
		));
	}


	@Override
	public Query groupBy(DExpr... expr) {
		return new QueryImpl(qc.withGroupBy(qc.groupBy.plus(new GroupByDef(GroupByType.normal, PList.val(expr)))));
	}

	@Override
	public Query groupByRollup(DExpr... expr) {
		return new QueryImpl(qc.withGroupBy(qc.groupBy.plus(new GroupByDef(GroupByType.rollup, PList.val(expr)))));
	}

	@Override
	public Query groupByCube(DExpr... expr) {
		return new QueryImpl(qc.withGroupBy(qc.groupBy.plus(new GroupByDef(GroupByType.cube, PList.val(expr)))));
	}

	@Override
	public Query having(EBool condition) {
		return new QueryImpl(qc.withHaving(condition));
	}

	@Override
	public Query orderByDesc(DExpr<?> expr) {
		return orderBy(new OrderBy(expr, OrderBy.Direction.desc));
	}

	@Override
	public Query orderByAsc(DExpr<?> expr) {
		return orderBy(new OrderBy(expr, OrderBy.Direction.asc));
	}

	private Query orderBy(OrderBy orderBy) {
		return new QueryImpl(qc.withOrderBy(qc.orderBy.plus(orderBy)));
	}

	@Override
	public Join leftJoin(Table table
	) {
		return new JoinImpl(this, JoinImpl.Type.left, table);
	}

	@Override
	public Join rightJoin(Table table
	) {
		return new JoinImpl(this, JoinImpl.Type.right, table);
	}

	@Override
	public Join innerJoin(Table table
	) {
		return new JoinImpl(this, JoinImpl.Type.inner, table);
	}

	@Override
	public Join fullJoin(Table table
	) {
		return new JoinImpl(this, JoinImpl.Type.full, table);
	}

	Query addJoin(JoinImpl join) {
		return new QueryImpl(qc.withJoins(qc.joins.plus(join)));
	}

	@Override
	public Query where(EBool whereExpr) {
		return new QueryImpl(qc.withWhere(whereExpr));
	}

	@Override
	public Query limit(ELong limit) {
		return new QueryImpl(qc.withLimitAndOffset(Tuple2.of(limit, null)));
	}

	@Override
	public Query limitAndOffset(ELong limit, ELong offset) {
		return new QueryImpl(qc.withLimitAndOffset(Tuple2.of(limit, offset)));
	}

	@Override
	public Query limit(long limit) {
		return limit(qc.context.val(limit));
	}

	@Override
	public Query limitAndOffset(long limit, long offset) {
		return limitAndOffset(qc.context.val(limit), qc.context.val(offset));
	}

	@Override
	public Query distinct() {
		return new QueryImpl(qc.withDistinct(true));
	}

	@Override
	public <E1 extends DExpr<J1>, J1> TypedSelection1<E1, J1> selection(E1 col1) {
		return new TypedSelection1Impl<>(this, col1);
	}

	@Override
	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2> TypedSelection1<ETuple2<E1, J1, E2, J2>, Tuple2<J1, J2>> selection(
		E1 sel1, E2 sel2
	) {
		return selection(qc.context.tuple(sel1, sel2));
	}

	@Override
	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3> TypedSelection1<ETuple3<E1, J1, E2, J2, E3, J3>, Tuple3<J1, J2, J3>> selection(
		E1 sel1, E2 sel2, E3 sel3
	) {
		return selection(qc.context.tuple(sel1, sel2, sel3));
	}

	@Override
	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4> TypedSelection1<ETuple4<E1, J1, E2, J2, E3, J3, E4, J4>, Tuple4<J1, J2, J3, J4>> selection(
		E1 sel1, E2 sel2, E3 sel3, E4 sel4) {
		return selection(qc.context.tuple(sel1, sel2, sel3, sel4));
	}

	@Override
	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5> TypedSelection1<ETuple5<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5>, Tuple5<J1, J2, J3, J4, J5>> selection(
		E1 sel1, E2 sel2, E3 sel3, E4 sel4, E5 sel5) {
		return selection(qc.context.tuple(sel1, sel2, sel3, sel4, sel5));
	}

	@Override
	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6> TypedSelection1<ETuple6<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6>, Tuple6<J1, J2, J3, J4, J5, J6>> selection(
		E1 sel1, E2 sel2, E3 sel3, E4 sel4, E5 sel5, E6 sel6) {
		return selection(qc.context.tuple(sel1, sel2, sel3, sel4, sel5, sel6));
	}

	@Override
	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6, E7 extends DExpr<J7>, J7> TypedSelection1<ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7>, Tuple7<J1, J2, J3, J4, J5, J6, J7>> selection(
		E1 sel1, E2 sel2, E3 sel3, E4 sel4, E5 sel5, E6 sel6, E7 sel7) {
		return selection(qc.context.tuple(sel1, sel2, sel3, sel4, sel5, sel6, sel7));
	}
}
