package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.SchemaHistory;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprDateTime;
import java.time.LocalDateTime;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import java.lang.String;

public class TSchemaHistory extends DTableExprImpl<SchemaHistory> {
	public  final	DExprDateTime	createddate;
	public  final	DExprString	packageName;
	public  final	DExprString	updateName;
	
	
	public TSchemaHistory(DExprDateTime createddate, DExprString packageName, DExprString updateName){
		super(
			PList.val(createddate, packageName, updateName),
			_scon -> _rr -> {
				LocalDateTime	_createddate = DImpl._get(createddate)._read(_scon,_rr);
				String	_packageName = DImpl._get(packageName)._read(_scon,_rr);
				String	_updateName = DImpl._get(updateName)._read(_scon,_rr);
				if(_createddate== null && _packageName== null && _updateName== null) { return null; }
				return new SchemaHistory(_createddate, _packageName, _updateName);
			}
		);
		this.createddate	=	createddate;
		this.packageName	=	packageName;
		this.updateName	=	updateName;
	}
	@Override
	protected  TSchemaHistory	_doWithAlias(String alias){
		return new TSchemaHistory(
			(DExprDateTime)DImpl._get(createddate)._withAlias(alias), 
			(DExprString)DImpl._get(packageName)._withAlias(alias), 
			(DExprString)DImpl._get(updateName)._withAlias(alias)
		);
	}
	public  static TSchemaHistory	cast(DExpr<SchemaHistory> expr){
		return (TSchemaHistory)expr;
	}
}
