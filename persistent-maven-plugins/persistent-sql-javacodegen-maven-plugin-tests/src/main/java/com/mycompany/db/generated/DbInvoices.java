package com.mycompany.db.generated;

import com.mycompany.db.generated.persistenttest.myschema.TInvoiceLineTable;
import com.persistentbit.sql.dsl.exprcontext.impl.GenericDbContext;
import com.mycompany.db.generated.persistenttest.myschema.TInvoiceTable;
import com.persistentbit.sql.dsl.generic.DbGeneric;
import com.mycompany.db.generated.persistenttest.myschema.TAPersonTable;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.mycompany.db.generated.persistenttest.myschema.TCompanyTable;

public class DbInvoices extends DbGeneric {
	public  final	TCompanyTable	company;
	public  final	TInvoiceTable	invoice;
	public  final	TInvoiceLineTable	invoiceLine;
	public  final	TAPersonTable	aPerson;
	
	
	public DbInvoices(GenericDbContext context){
		super(context);
		this.company = new TCompanyTable(context.forTable("persistenttest", "company"));
		this.invoice = new TInvoiceTable(context.forTable("persistenttest", "invoice"));
		this.invoiceLine = new TInvoiceLineTable(context.forTable("persistenttest", "invoice_line"));
		this.aPerson = new TAPersonTable(context.forTable("persistenttest", "person"));
	}
	public DbInvoices(){
		this(new GenericDbContext());
	}
}
