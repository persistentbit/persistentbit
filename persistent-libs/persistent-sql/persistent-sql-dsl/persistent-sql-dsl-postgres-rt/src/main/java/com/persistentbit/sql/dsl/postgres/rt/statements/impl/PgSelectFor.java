package com.persistentbit.sql.dsl.postgres.rt.statements.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.Sql;
import com.persistentbit.sql.dsl.postgres.rt.statements.PgQuery;
import com.persistentbit.sql.dsl.tables.Table;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/18
 */
public class PgSelectFor{

	private PgQueryImpl  query;
	private String       forWhat;
	private PList<Table> tables;
	private boolean      noWait;
	private boolean      skipLocked;

	protected PgSelectFor(PgQueryImpl query, String forWhat,
						  PList<Table> tables, boolean noWait, boolean skipLocked) {
		this.query = query;
		this.forWhat = forWhat;
		this.tables = tables;
		this.noWait = noWait;
		this.skipLocked = skipLocked;
	}

	public PgSelectFor(PgQueryImpl query, String forWhat) {
		this(query, forWhat, PList.empty(), false, false);
	}

	public PgQuery noWait() {
		return query.with_for_what(new PgSelectFor(query, forWhat, tables, true, skipLocked));
	}

	public PgQuery skipLocked() {
		return query.with_for_what(new PgSelectFor(query, forWhat, tables, noWait, true));
	}

	public PgSelectFor of(Table... tables) {
		return new PgSelectFor(query, forWhat, PList.val(tables), noWait, skipLocked);
	}

	public PgQuery query() {
		return query.with_for_what(this);
	}

	public Sql toSql() {
		Sql sql = Sql.sql(forWhat + " ");
		if(tables.isEmpty() == false) {
			sql =
				sql.add(" OF " + tables.map(t -> query.getQueryCtx().getContext().getFromTableName(t)).toString(", "));
		}
		sql = noWait ? sql.add(" NO WAIT") : sql;
		sql = skipLocked ? sql.add(" SKIP LOCKED") : sql;
		return sql;
	}
}
