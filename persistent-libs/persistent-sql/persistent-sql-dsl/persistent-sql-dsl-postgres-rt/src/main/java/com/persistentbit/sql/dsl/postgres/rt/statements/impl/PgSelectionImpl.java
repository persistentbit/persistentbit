package com.persistentbit.sql.dsl.postgres.rt.statements.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.Sql;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.statements.select.impl.QueryCtx;
import com.persistentbit.sql.dsl.statements.select.impl.SelectionImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/18
 */
public class PgSelectionImpl<E1 extends DExpr<J1>, J1> extends SelectionImpl<E1, J1>{

	private PList<PgSelectFor> selectFor;

	public PgSelectionImpl(QueryCtx queryCtx, PList<PgSelectFor> selectFor, E1 col1) {
		super(queryCtx, col1);
		this.selectFor = selectFor;
	}

	@Override
	protected Sql toSql(String preFixAlias) {
		Sql sql = super.toSql(preFixAlias);
		if(selectFor.isEmpty()) {
			return sql;
		}
		sql = sql.add(selectFor.map(sf -> sf.toSql()), " ");
		return sql;
	}
}
