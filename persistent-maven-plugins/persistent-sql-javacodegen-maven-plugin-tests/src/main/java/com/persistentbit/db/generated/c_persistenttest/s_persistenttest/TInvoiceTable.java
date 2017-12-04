package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Invoice;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import java.lang.String;

public class TInvoiceTable extends TInvoice implements DExprTable<Invoice> {
	
	
	public TInvoiceTable(DbTableContext tableContext){
		super(tableContext.createExprLong("id"),tableContext.createExprString("invoice_nummer"),tableContext.createExprLong("from_company_id"),tableContext.createExprLong("to_company_id"));
		this._tableContext = tableContext;
	}
	public  TInvoiceTable	alias(String tableAlias){
		return new TInvoiceTable(_tableContext.withTableAlias(tableAlias));
	}
	@Override
	public  Query	query(){
		return _tableContext.createQuery(this);
	}
	@Override
	public  TInvoice	all(){
		return this;
	}
}
