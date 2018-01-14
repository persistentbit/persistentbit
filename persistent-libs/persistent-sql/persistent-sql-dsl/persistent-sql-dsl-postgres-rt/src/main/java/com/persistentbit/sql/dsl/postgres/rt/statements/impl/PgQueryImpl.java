package com.persistentbit.sql.dsl.postgres.rt.statements.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.orderby.OrderBy;
import com.persistentbit.sql.dsl.postgres.rt.statements.PgQuery;
import com.persistentbit.sql.dsl.statements.select.Join;
import com.persistentbit.sql.dsl.statements.select.Selection;
import com.persistentbit.sql.dsl.statements.select.impl.AbstractQueryImpl;
import com.persistentbit.sql.dsl.statements.select.impl.JoinImpl;
import com.persistentbit.sql.dsl.statements.select.impl.QueryCtx;
import com.persistentbit.sql.dsl.tables.Table;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/18
 */
public class PgQueryImpl extends AbstractQueryImpl<PgQuery, Join> implements PgQuery{

	@Nullable
	private final PList<PgSelectFor> selectFor;

	protected PgQueryImpl(QueryCtx qc, PList<PgSelectFor> selectFor) {
		super(qc);
		this.selectFor = selectFor;
	}

	public PgQueryImpl(ExprContext context, PList<Table> from) {
		this(QueryCtx.build(b -> b
			.setContext(context)
			.setFrom(from)
			.setOrderBy(OrderBy.empty())
		), PList.empty());
	}

	public QueryCtx getQueryCtx() {
		return qc;
	}


	@Override
	protected PgQuery copy(QueryCtx ctx) {
		return new PgQueryImpl(ctx, selectFor);
	}

	@Override
	protected Join createJoin(JoinImpl.Type type, Table table) {
		return new JoinImpl<>(j -> addJoin(j), qc, type, table);
	}

	@Override
	protected <E1 extends DExpr<J1>, J1> Selection<E1, J1> createSelection(E1 col) {
		return new PgSelectionImpl<E1, J1>(qc, selectFor, col);
	}

	public PgSelectFor forUpdate() {
		return new PgSelectFor(this, "UPDATE");
	}

	@Override
	public PgSelectFor forNoKeyUpdate() {
		return new PgSelectFor(this, "NO KEY UPDATE");
	}

	@Override
	public PgSelectFor forShare() {
		return new PgSelectFor(this, "SHARE");
	}

	@Override
	public PgSelectFor forKeyShare() {
		return new PgSelectFor(this, "KEY SHARE");
	}

	public PgQueryImpl with_for_what(PgSelectFor selectFor) {
		return new PgQueryImpl(qc, this.selectFor.plus(selectFor));
	}
}
