package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.SchemaHistory;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.DExprDateTime;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.tuples.Tuple2;

public class TSchemaHistory extends DTable<SchemaHistory> {
	public  final	DExprDateTime	createddate;
	public  final	DExprString	packageName;
	public  final	DExprString	updateName;
	
	
	public TSchemaHistory(DbTableContext context){
		super(context);
		this.createddate	=	context.createExprDateTime(this, "createddate");
		this.packageName	=	context.createExprString(this, "package_name");
		this.updateName	=	context.createExprString(this, "update_name");
		super._all = PList.val(Tuple2.of("createddate",createddate), Tuple2.of("packageName",packageName), Tuple2.of("updateName",updateName));
	}
	public  TSchemaHistory	alias(String aliasName){
		return new TSchemaHistory(_tableContext.withAlias(aliasName));
	}
}
