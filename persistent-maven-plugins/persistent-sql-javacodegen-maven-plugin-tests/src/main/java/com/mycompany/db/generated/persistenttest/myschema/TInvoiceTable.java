package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.updates.Update;
import com.persistentbit.sql.dsl.generic.inserts.Insert;
import com.persistentbit.sql.dsl.generic.inserts.InsertResult;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.collections.PList;
import com.mycompany.db.generated.persistenttest.myschema.Invoice;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import java.lang.String;

public class TInvoiceTable extends TInvoice implements DExprTable<Invoice> {
	
	
	public TInvoiceTable(DbTableContext tableContext){
		super(tableContext.createExprLong("id"),tableContext.createExprString("invoice_nummer"),tableContext.createExprLong("from_company_id"),tableContext.createExprLong("to_company_id"));
		this._tableContext = tableContext;
		this._insertFieldNames = PList.val("id", "invoice_nummer", "from_company_id", "to_company_id");
		this._autoGenKeyFieldNames = PList.val("id");
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
	public  TInvoice	val(Invoice v){
		DbContext db = _tableContext.getDbContext();
		return new TInvoice(
			db.val(v.getId()), db.val(v.getInvoiceNummer()), db.val(v.getFromCompanyId()), db.val(v.getToCompanyId())
		);
	}
	public  Update	update(){
		return new Update(_tableContext.getDbContext(),this);
	}
	public  DbWork<Integer>	update(Invoice record){
		DbContext db = _tableContext.getDbContext();
		return update()
			.set(this.invoiceNummer, db.val(record.getInvoiceNummer()))
			.set(this.fromCompanyId, db.val(record.getFromCompanyId()))
			.set(this.toCompanyId, db.val(record.getToCompanyId()))
			.where(this.id.eq(db.val(record.getId())));
	}
	public  DbWork<Invoice>	insert(Invoice record){
		return new Insert(_tableContext.getDbContext(),this)
			.row(val(record))
			.withAutoGenerated(id)
			.map(insertResult -> {
				PList keys = insertResult.getAutoGenKeys().head();
				return record
					.withId((long)keys.get(0))
				;
			});
	}
}
