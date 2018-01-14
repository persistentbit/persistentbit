package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.orderby.OrderBy;
import com.persistentbit.sql.dsl.statements.select.Join;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.Selection;
import com.persistentbit.sql.dsl.tables.Table;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class QueryImpl extends AbstractQueryImpl<Query, Join> implements Query{

	protected QueryImpl(QueryCtx qc) {
		super(qc);
	}

	public QueryImpl(ExprContext context, PList<Table> from) {
		this(QueryCtx.build(b -> b
			.setContext(context)
			.setFrom(from)
			.setOrderBy(OrderBy.empty())
		));
	}
	@Override
	protected Query copy(QueryCtx ctx) {
		return new QueryImpl(ctx);
	}

	@Override
	protected Join createJoin(JoinImpl.Type type, Table table) {
		return new JoinImpl<>(j -> addJoin(j), qc, type, table);
	}

	@Override
	protected <E1 extends DExpr<J1>, J1> Selection<E1, J1> createSelection(E1 col) {
		return new SelectionImpl<>(qc, col);
	}
}
