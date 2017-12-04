package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Person;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import java.lang.String;

public class TPersonTable extends TPerson implements DExprTable<Person> {
	
	
	public TPersonTable(DbTableContext tableContext){
		super(tableContext.createExprLong("id"),tableContext.createExprString("user_name"),tableContext.createExprString("password"),tableContext.createExprString("street"),tableContext.createExprInt( "house_number"),tableContext.createExprString("bus_number"),tableContext.createExprString("postalcode"),tableContext.createExprString("city"),tableContext.createExprString("country"));
		this._tableContext = tableContext;
	}
	public  TPersonTable	alias(String tableAlias){
		return new TPersonTable(_tableContext.withTableAlias(tableAlias));
	}
	@Override
	public  Query	query(){
		return _tableContext.createQuery(this);
	}
	@Override
	public  TPerson	all(){
		return this;
	}
}
