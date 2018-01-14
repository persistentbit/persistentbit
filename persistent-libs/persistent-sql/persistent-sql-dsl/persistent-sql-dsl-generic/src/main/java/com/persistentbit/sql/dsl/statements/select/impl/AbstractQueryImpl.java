package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.orderby.OrderBy;
import com.persistentbit.sql.dsl.orderby.OrderByItem;
import com.persistentbit.sql.dsl.statements.select.Join;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.Selection;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.tuples.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/18
 */
public abstract class AbstractQueryImpl<Q extends Query, J extends Join>{

	protected abstract Q copy(QueryCtx ctx);

	protected final QueryCtx qc;

	protected AbstractQueryImpl(QueryCtx qc) {
		this.qc = qc;
	}

	protected AbstractQueryImpl(ExprContext context, PList<Table> from) {
		this(QueryCtx.build(b -> b
			.setContext(context)
			.setFrom(from)
			.setOrderBy(OrderBy.empty())
		));
	}


	public Q groupBy(DExpr... expr) {
		return copy(qc.withGroupBy(qc.groupBy.plus(new GroupByDef(GroupByType.normal, PList.val(expr)))));
	}

	public Q groupByRollup(DExpr... expr) {
		return copy(qc.withGroupBy(qc.groupBy.plus(new GroupByDef(GroupByType.rollup, PList.val(expr)))));
	}


	public Q groupByCube(DExpr... expr) {
		return copy(qc.withGroupBy(qc.groupBy.plus(new GroupByDef(GroupByType.cube, PList.val(expr)))));
	}


	public Q having(EBool condition) {
		return copy(qc.withHaving(condition));
	}


	public Q orderByDesc(DExpr<?> expr) {
		return orderBy(new OrderByItem(expr, OrderByItem.Direction.desc));
	}


	public Q orderByAsc(DExpr<?> expr) {
		return orderBy(new OrderByItem(expr, OrderByItem.Direction.asc));
	}

	private Q orderBy(OrderByItem orderBy) {
		return copy(qc.withOrderBy(qc.orderBy.add(orderBy)));
	}

	protected abstract J createJoin(JoinImpl.Type type, Table table);

	public Join leftJoin(Table table
	) {
		return createJoin(JoinImpl.Type.left, table);
	}


	public Join rightJoin(Table table
	) {
		return createJoin(JoinImpl.Type.right, table);
	}

	public Join innerJoin(Table table
	) {
		return createJoin(JoinImpl.Type.inner, table);
	}


	public Join fullJoin(Table table
	) {
		return createJoin(JoinImpl.Type.full, table);
	}

	public Q addJoin(JoinImpl join) {
		return copy(qc.withJoins(qc.joins.plus(join)));
	}


	public Q where(EBool whereExpr) {
		return copy(qc.withWhere(whereExpr));
	}


	public Q limit(ELong limit) {
		return copy(qc.withLimitAndOffset(Tuple2.of(limit, null)));
	}


	public Q limitAndOffset(ELong limit, ELong offset) {
		return copy(qc.withLimitAndOffset(Tuple2.of(limit, offset)));
	}


	public Q limit(long limit) {
		return limit(qc.context.val(limit));
	}


	public Q limitAndOffset(long limit, long offset) {
		return limitAndOffset(qc.context.val(limit), qc.context.val(offset));
	}


	public Q distinct() {
		return copy(qc.withDistinct(true));
	}

	protected abstract <E1 extends DExpr<J1>, J1> Selection<E1, J1> createSelection(E1 col);

	public <E1 extends DExpr<J1>, J1> Selection<E1, J1> selection(E1 col1) {
		return createSelection(col1);
	}


	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2> Selection<ETuple2<E1, J1, E2, J2>, Tuple2<J1, J2>> selection(
		E1 sel1, E2 sel2
	) {
		return selection(qc.context.tuple(sel1, sel2));
	}


	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3> Selection<ETuple3<E1, J1, E2, J2, E3, J3>, Tuple3<J1, J2, J3>> selection(
		E1 sel1, E2 sel2, E3 sel3
	) {
		return selection(qc.context.tuple(sel1, sel2, sel3));
	}


	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4> Selection<ETuple4<E1, J1, E2, J2, E3, J3, E4, J4>, Tuple4<J1, J2, J3, J4>> selection(
		E1 sel1, E2 sel2, E3 sel3, E4 sel4) {
		return selection(qc.context.tuple(sel1, sel2, sel3, sel4));
	}


	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5> Selection<ETuple5<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5>, Tuple5<J1, J2, J3, J4, J5>> selection(
		E1 sel1, E2 sel2, E3 sel3, E4 sel4, E5 sel5) {
		return selection(qc.context.tuple(sel1, sel2, sel3, sel4, sel5));
	}


	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6> Selection<ETuple6<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6>, Tuple6<J1, J2, J3, J4, J5, J6>> selection(
		E1 sel1, E2 sel2, E3 sel3, E4 sel4, E5 sel5, E6 sel6) {
		return selection(qc.context.tuple(sel1, sel2, sel3, sel4, sel5, sel6));
	}


	public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6, E7 extends DExpr<J7>, J7> Selection<ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7>, Tuple7<J1, J2, J3, J4, J5, J6, J7>> selection(
		E1 sel1, E2 sel2, E3 sel3, E4 sel4, E5 sel5, E6 sel6, E7 sel7) {
		return selection(qc.context.tuple(sel1, sel2, sel3, sel4, sel5, sel6, sel7));
	}

}
