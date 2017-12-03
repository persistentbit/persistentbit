package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.SchemaHistory;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprDateTime;
import java.time.LocalDateTime;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.tuples.Tuple2;
import java.lang.String;

public class TSchemaHistoryTable extends DTable<SchemaHistory, TSchemaHistory> {
	public  final	DExprDateTime	createddate;
	public  final	DExprString	packageName;
	public  final	DExprString	updateName;
	
	
	public TSchemaHistoryTable(DbTableContext context){
		super(context);
		this.createddate	=	context.createExprDateTime(this, "createddate");
		this.packageName	=	context.createExprString(this, "package_name");
		this.updateName	=	context.createExprString(this, "update_name");
		super._all = PList.val(Tuple2.of("createddate",createddate), Tuple2.of("packageName",packageName), Tuple2.of("updateName",updateName));
		
		_recordReader = _scon -> _rr -> {
			LocalDateTime	createddate = DImpl._get(this.createddate)._read(_scon,_rr);
			String	packageName = DImpl._get(this.packageName)._read(_scon,_rr);
			String	updateName = DImpl._get(this.updateName)._read(_scon,_rr);
			if(createddate== null && packageName== null && updateName== null) { return null; }
			return new SchemaHistory(createddate, packageName, updateName);
		};
		_doWithAlias = alias -> new TSchemaHistory(_tableContext.withAlias(alias));
	}
	public  TSchemaHistoryTable	withTableAlias(String tableAlias){
		return new TSchemaHistoryTable(_tableContext.withTableAlias(tableAlias));
	}
	public  static TSchemaHistoryTable	cast(DExpr<SchemaHistory> expr){
		return (TSchemaHistoryTable)expr;
	}
}
