package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.ETuple2;
import com.persistentbit.sql.dsl.expressions.ETuple3;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.select.Join;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.TypedSelection1;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.tuples.Tuple3;
import com.persistentbit.utils.exceptions.ToDo;

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
	public Query limit(long limit) {
		throw new ToDo();
	}

	@Override
	public Query limitAndOffset(long limit, long offset) {
		throw new ToDo();
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
}
