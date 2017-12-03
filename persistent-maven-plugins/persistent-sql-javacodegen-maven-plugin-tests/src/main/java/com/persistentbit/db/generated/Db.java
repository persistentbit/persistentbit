package com.persistentbit.db.generated;

import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.TSchemaHistory;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.TInvoice;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.TPerson;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.TInvoiceLine;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.postgres.rt.DbPostgres;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.TCompany;

public class Db extends DbPostgres {
	public  final	TCompany	company;
	public  final	TInvoice	invoice;
	public  final	TInvoiceLine	invoiceLine;
	public  final	TPerson	person;
	public  final	TSchemaHistory	schemaHistory;
	
	
	public Db(PostgresDbContext context){
		super(context);
		this.company = new TCompany(context.forTable("persistenttest", "company"));
		this.invoice = new TInvoice(context.forTable("persistenttest", "invoice"));
		this.invoiceLine = new TInvoiceLine(context.forTable("persistenttest", "invoice_line"));
		this.person = new TPerson(context.forTable("persistenttest", "person"));
		this.schemaHistory = new TSchemaHistory(context.forTable("persistenttest", "schema_history"));
	}
	public Db(){
		this(new PostgresDbContext());
	}
}
