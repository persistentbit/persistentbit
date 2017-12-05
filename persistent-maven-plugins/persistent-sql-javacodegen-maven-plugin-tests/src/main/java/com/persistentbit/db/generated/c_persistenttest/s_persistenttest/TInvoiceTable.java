package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.inserts.Insert;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Invoice;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import java.lang.String;

public class TInvoiceTable extends TInvoice implements DExprTable<Invoice> {
	
	
	public TInvoiceTable(DbTableContext tableContext){
		super(tableContext.createExprLong("id"),tableContext.createExprString("invoice_nummer"),tableContext.createExprLong("from_company_id"),tableContext.createExprLong("to_company_id"));
		this._tableContext = tableContext;
		this._insertFieldNames = PList.val("id", "invoice_nummer", "from_company_id", "to_company_id");
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
			db.val(v.getId().orElse(null)), db.val(v.getInvoiceNummer()), db.val(v.getFromCompanyId()), db.val(v.getToCompanyId())
		);
	}
	public  DbWork<Invoice>	insert(Invoice record){
		DbWork<Integer> count = new Insert<>(this._tableContext.getDbContext(), this, val(record));
		return count.flatMap(c -> c==0 ? Result.empty() : Result.success(record));
	}
}
