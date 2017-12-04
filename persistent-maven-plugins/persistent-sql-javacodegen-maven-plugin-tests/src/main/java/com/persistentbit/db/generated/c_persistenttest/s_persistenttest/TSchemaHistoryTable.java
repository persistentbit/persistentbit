package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.SchemaHistory;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import java.time.LocalDateTime;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import java.lang.String;

public class TSchemaHistoryTable extends TSchemaHistory implements DExprTable<SchemaHistory> {
	
	
	public TSchemaHistoryTable(DbTableContext tableContext){
		super(tableContext.createExprDateTime("createddate"),tableContext.createExprString("package_name"),tableContext.createExprString("update_name"));
		this._tableContext = tableContext;
	}
	public  TSchemaHistoryTable	alias(String tableAlias){
		return new TSchemaHistoryTable(_tableContext.withTableAlias(tableAlias));
	}
	@Override
	public  Query	query(){
		return _tableContext.createQuery(this);
	}
	@Override
	public  TSchemaHistory	all(){
		return this;
	}
}
