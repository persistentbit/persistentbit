package com.persistentbit.db.generated;

import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.*;
import com.persistentbit.sql.dsl.postgres.rt.DbPostgres;
import com.persistentbit.sql.dsl.postgres.rt.PostgresDbContext;

public class Db extends DbPostgres {
	public  final	TCompanyTable	company;
	public  final	TInvoiceTable	invoice;
	public  final	TInvoiceLineTable	invoiceLine;
	public  final	TPersonTable	person;
	public  final	TSchemaHistoryTable	schemaHistory;
	
	
	public Db(PostgresDbContext context){
		super(context);
		this.company = new TCompanyTable(context.forTable("persistenttest", "company"));
		this.invoice = new TInvoiceTable(context.forTable("persistenttest", "invoice"));
		this.invoiceLine = new TInvoiceLineTable(context.forTable("persistenttest", "invoice_line"));
		this.person = new TPersonTable(context.forTable("persistenttest", "person"));
		this.schemaHistory = new TSchemaHistoryTable(context.forTable("persistenttest", "schema_history"));
	}
	public Db(){
		this(new PostgresDbContext());
	}
	public  TCompany	val(Company v){
		return new TCompany(
			val(v.getId()), val(v.getCompanyName()), val(v.getAdresStreet()), val(v.getAdresHouseNumber()), val(v.getAdresBusNumber().orElse(null)), val(v.getAdresPostalcode()), val(v.getAdresCity()), val(v.getAdresCountry()), val(v.getOwnerPersonId().orElse(null))
		);
	}
	public  TInvoice	val(Invoice v){
		return new TInvoice(
			val(v.getId()), val(v.getInvoiceNummer()), val(v.getFromCompanyId()), val(v.getToCompanyId())
		);
	}
	public  TInvoiceLine	val(InvoiceLine v){
		return new TInvoiceLine(
			val(v.getId()), val(v.getInvoiceId()), val(v.getProduct().orElse(null))
		);
	}
	public  TPerson	val(Person v){
		return new TPerson(
			val(v.getId()), val(v.getUserName()), val(v.getPassword()), val(v.getStreet()), val(v.getHouseNumber()), val(v.getBusNumber().orElse(null)), val(v.getPostalcode()), val(v.getCity()), val(v.getCountry())
		);
	}
	public  TSchemaHistory	val(SchemaHistory v){
		return new TSchemaHistory(
			val(v.getCreateddate()), val(v.getPackageName()), val(v.getUpdateName())
		);
	}
}
