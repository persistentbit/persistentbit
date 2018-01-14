package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.dsl.tables.TableImpl;
import com.persistentbit.sql.work.DbWork;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public class SubQuery1<E1 extends DExpr<J1>,J1> implements TableImpl<E1,J1>{

	private final SelectionImpl<E1, J1> orgSelection;
	private final String                name;
	public final  E1                    v1;

	public SubQuery1(SelectionImpl<E1, J1> orgSelection, String name) {
		this.orgSelection = orgSelection;
		this.name = name;
		ExprContext	context = orgSelection.context;
		this.v1 = context.buildAlias(orgSelection.col1, name + "." + "v1_");
	}

	public SqlWithParams	toSql() {
		return SqlWithParams
				   .sql("(")
			.add(orgSelection.toSql("v1_"))
			.add(") AS " + name);
	}


	@Override
	public Table as(String aliasName) {
		return null;
	}

	@Override
	public E1 all() {
		return v1;
	}


	@Override
	public Class<E1> getTypeClass() {
		Class cls = SubQuery1.class;
		return cls;
	}

	@Override
	public SqlWithParams getFromName(String defaultCatalog, String defaultSchema) {
		return toSql();
	}

	@Override
	public Query query() {
		return new QueryImpl(orgSelection.context, PList.val(this));
	}

	@Override
	public DbWork<PList<J1>> selectAll() {
		return query().selection(all()).list();
	}
}
