package com.mycompany.db.generated;

import com.mycompany.db.generated.persistenttest.myschema.TInvoiceLineTable;
import com.persistentbit.sql.dsl.exprcontext.impl.GenericDbContext;
import com.mycompany.db.generated.persistenttest.myschema.TInvoiceTable;
import com.persistentbit.sql.dsl.generic.DbGeneric;
import com.mycompany.db.generated.persistenttest.myschema.TAPersonTable;
import com.mycompany.db.generated.persistenttest.myschema.TAuthAppTable;
import com.mycompany.db.generated.persistenttest.myschema.TAuthUserTable;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.mycompany.db.generated.persistenttest.myschema.TCompanyTable;
import com.mycompany.db.generated.persistenttest.myschema.TAuthUserRememberMeTable;

public class DbInvoices extends DbGeneric {
	public  final	TAuthAppTable	authApp;
	public  final	TAuthUserTable	authUser;
	public  final	TAuthUserRememberMeTable	authUserRememberMe;
	public  final	TCompanyTable	company;
	public  final	TInvoiceTable	invoice;
	public  final	TInvoiceLineTable	invoiceLine;
	public  final	TAPersonTable	aPerson;
	
	
	public DbInvoices(GenericDbContext context){
		super(context);
		this.authApp = new TAuthAppTable(context.forTable("persistenttest", "auth_app"));
		this.authUser = new TAuthUserTable(context.forTable("persistenttest", "auth_user"));
		this.authUserRememberMe = new TAuthUserRememberMeTable(context.forTable("persistenttest", "auth_user_remember_me"));
		this.company = new TCompanyTable(context.forTable("persistenttest", "company"));
		this.invoice = new TInvoiceTable(context.forTable("persistenttest", "invoice"));
		this.invoiceLine = new TInvoiceLineTable(context.forTable("persistenttest", "invoice_line"));
		this.aPerson = new TAPersonTable(context.forTable("persistenttest", "person"));
	}
	public DbInvoices(){
		this(new GenericDbContext());
	}
}
