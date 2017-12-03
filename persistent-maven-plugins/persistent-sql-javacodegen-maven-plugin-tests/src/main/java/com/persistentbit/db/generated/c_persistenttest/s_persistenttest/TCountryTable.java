package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;


/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public class TCountryTable extends TCountry implements DExprTable<Country>{
	private final DbTableContext _tableContext;
	public TCountryTable(DbTableContext tableContext) {
		super(
			tableContext.createExprInt("id")
			,tableContext.createExprString("name")
			,tableContext.createExprString("code")
		);
		this._tableContext = tableContext;
	}

	@Override
	public Query query() {
		return _tableContext.createQuery(this);
	}

	@Override
	public TCountry all() {
		return this;
	}

	public TCountryTable alias(String tableAlias){
		return new TCountryTable(_tableContext.withTableAlias(tableAlias));
	}


}
