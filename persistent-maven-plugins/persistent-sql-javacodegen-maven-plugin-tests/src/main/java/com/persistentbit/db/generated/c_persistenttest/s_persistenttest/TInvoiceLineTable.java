package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.inserts.Insert;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.InvoiceLine;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import java.lang.String;

public class TInvoiceLineTable extends TInvoiceLine implements DExprTable<InvoiceLine> {
	
	
	public TInvoiceLineTable(DbTableContext tableContext){
		super(tableContext.createExprLong("id"),tableContext.createExprLong("invoice_id"),tableContext.createExprString("product"));
		this._tableContext = tableContext;
		this._insertFieldNames = PList.val("id", "invoice_id", "product");
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
	public  TInvoiceLine	val(InvoiceLine v){
		DbContext db = _tableContext.getDbContext();
		return new TInvoiceLine(
			db.val(v.getId().orElse(null)), db.val(v.getInvoiceId()), db.val(v.getProduct().orElse(null))
		);
	}
	public  DbWork<InvoiceLine>	insert(InvoiceLine record){
		DbWork<Integer> count = new Insert<>(this._tableContext.getDbContext(), this, val(record));
		return count.flatMap(c -> c==0 ? Result.empty() : Result.success(record));
	}
}
