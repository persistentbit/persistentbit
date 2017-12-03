package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.InvoiceLine;
import java.lang.String;

public class TInvoiceLineTable extends DTable<InvoiceLine, TInvoiceLine> {
	public  final	DExprLong	id;
	public  final	DExprLong	invoiceId;
	public  final	DExprString	product;
	
	
	public TInvoiceLineTable(DbTableContext context){
		super(context);
		this.id	=	context.createExprLong(this, "id");
		this.invoiceId	=	context.createExprLong(this, "invoice_id");
		this.product	=	context.createExprString(this, "product");
		super._all = PList.val(Tuple2.of("id",id), Tuple2.of("invoiceId",invoiceId), Tuple2.of("product",product));
		
		_recordReader = _scon -> _rr -> {
			Long	id = DImpl._get(this.id)._read(_scon,_rr);
			Long	invoiceId = DImpl._get(this.invoiceId)._read(_scon,_rr);
			String	product = DImpl._get(this.product)._read(_scon,_rr);
			if(id== null && invoiceId== null && product== null) { return null; }
			return new InvoiceLine(id, invoiceId, product);
		};
		_doWithAlias = alias -> new TInvoiceLine(_tableContext.withAlias(alias));
	}
	public  TInvoiceLineTable	withTableAlias(String tableAlias){
		return new TInvoiceLineTable(_tableContext.withTableAlias(tableAlias));
	}
	public  static TInvoiceLineTable	cast(DExpr<InvoiceLine> expr){
		return (TInvoiceLineTable)expr;
	}
}
