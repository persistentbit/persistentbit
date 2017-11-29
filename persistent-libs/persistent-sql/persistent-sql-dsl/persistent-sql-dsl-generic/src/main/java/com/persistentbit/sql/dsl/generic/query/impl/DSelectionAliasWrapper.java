package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 29/11/17
 */
public class DSelectionAliasWrapper<T> extends DImpl<T>{
	private String parentAlias;
	private String itemAlias;
	private DExpr<T> content;

	public DSelectionAliasWrapper(String parentAlias, String itemAlias, DExpr<T> content) {
		this.parentAlias = parentAlias;
		this.itemAlias = itemAlias;
		this.content = content;
	}

	@Override
	public SqlWithParams toSqlSelection(DbSqlContext context) {
		return new SqlWithParams("(")
			.add(DImpl._get(content).toSqlSelection(context))
			.add(") AS " + itemAlias);
	}

	@Override
	public SqlWithParams toSqlSelectableFrom(DbSqlContext context) {
		return toSql(context);
	}

	@Override
	public SqlWithParams toSql(DbSqlContext context) {
		String fullAlias = parentAlias == null ? itemAlias : parentAlias + "." + itemAlias;
		return new SqlWithParams(fullAlias);
	}

	@Override
	public T read(DbSqlContext context, RowReader rowReader
	) {
		return DImpl._get(content).read(context, rowReader);
	}
}
