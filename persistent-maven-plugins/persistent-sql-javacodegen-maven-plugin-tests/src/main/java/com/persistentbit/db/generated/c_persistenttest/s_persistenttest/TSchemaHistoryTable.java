package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.inserts.Insert;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.SchemaHistory;
import com.persistentbit.sql.dsl.generic.inserts.InsertResult;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import java.time.LocalDateTime;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import java.lang.String;

public class TSchemaHistoryTable extends TSchemaHistory implements DExprTable<SchemaHistory> {
	
	
	public TSchemaHistoryTable(DbTableContext tableContext){
		super(tableContext.createExprDateTime("createddate"),tableContext.createExprString("package_name"),tableContext.createExprString("update_name"));
		this._tableContext = tableContext;
		this._insertFieldNames = PList.val("createddate", "package_name", "update_name");
		this._autoGenKeyFieldNames = PList.val();
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
	public  TSchemaHistory	val(SchemaHistory v){
		DbContext db = _tableContext.getDbContext();
		return new TSchemaHistory(
			db.val(v.getCreateddate()), db.val(v.getPackageName()), db.val(v.getUpdateName())
		);
	}
	public  DbWork<SchemaHistory>	insert(SchemaHistory record){
		return new Insert(_tableContext.getDbContext(),this)
			.row(val(record))
			.map(ir -> record);
	}
}
