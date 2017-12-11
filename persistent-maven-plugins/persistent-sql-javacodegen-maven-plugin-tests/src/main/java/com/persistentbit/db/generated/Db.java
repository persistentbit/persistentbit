package com.persistentbit.db.generated;

import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.*;
import com.persistentbit.sql.dsl.exprcontext.impl.GenericDbContext;
import com.persistentbit.sql.dsl.generic.DbGeneric;

public class Db extends DbGeneric {
	public  final	TCompanyTable	company;
	public  final	TInvoiceTable	invoice;
	public  final	TInvoiceLineTable	invoiceLine;
	public  final	TPersonTable	person;
	public  final	TSchemaHistoryTable	schemaHistory;
	
	
	public Db(GenericDbContext context){
		super(context);
		this.company = new TCompanyTable(context.forTable("persistenttest", "company"));
		this.invoice = new TInvoiceTable(context.forTable("persistenttest", "invoice"));
		this.invoiceLine = new TInvoiceLineTable(context.forTable("persistenttest", "invoice_line"));
		this.person = new TPersonTable(context.forTable("persistenttest", "person"));
		this.schemaHistory = new TSchemaHistoryTable(context.forTable("persistenttest", "schema_history"));
	}
	public Db(){
		this(new GenericDbContext());
	}
}
