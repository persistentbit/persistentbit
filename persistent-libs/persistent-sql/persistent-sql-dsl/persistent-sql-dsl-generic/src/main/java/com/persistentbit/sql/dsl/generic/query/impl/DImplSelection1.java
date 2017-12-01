package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.DSelection1;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection1<T> extends DImplSelectionAbstract<T> implements DSelection1<T>{

	public DImplSelection1(QueryImpl query,
						   PList<DExpr> columns,String aliasName
	) {
		super(query, columns,aliasName);
	}

	@Override
	public DExpr<T> v1() {
		return (DExpr<T>)getWithAlias(0);
	}


	@Override
	public T read(DbSqlContext context, RowReader rowReader
	) {
		return DImpl._get(v1()).read(context,rowReader);
	}

	@Override
	public DSelection1<T> asTableExpr(String aliasName) {
		return new DImplSelection1<>(query,columns,aliasName);
	}

	@Override
	public Query query() {
		return new QueryImpl(query.dbContext,PList.val(this));
	}
}
