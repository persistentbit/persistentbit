package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.InvoiceLine;
import java.lang.String;

public class TInvoiceLineTable extends TInvoiceLine implements DExprTable<InvoiceLine> {
	
	
	public TInvoiceLineTable(DbTableContext tableContext){
		super(tableContext.createExprLong("id"),tableContext.createExprLong("invoice_id"),tableContext.createExprString("product"));
		this._tableContext = tableContext;
	}
	public  TInvoiceLineTable	alias(String tableAlias){
		return new TInvoiceLineTable(_tableContext.withTableAlias(tableAlias));
	}
	@Override
	public  Query	query(){
		return _tableContext.createQuery(this);
	}
	@Override
	public  TInvoiceLine	all(){
		return this;
	}
}
