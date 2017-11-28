package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

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

public class TInvoiceLine extends DTable<InvoiceLine> {
	public  final	DExprLong	id;
	public  final	DExprLong	invoiceId;
	public  final	DExprString	product;
	
	
	public TInvoiceLine(DbTableContext context){
		super(context);
		this.id	=	context.createExprLong(this, "id");
		this.invoiceId	=	context.createExprLong(this, "invoice_id");
		this.product	=	context.createExprString(this, "product");
		super._all = PList.val(Tuple2.of("id",id), Tuple2.of("invoiceId",invoiceId), Tuple2.of("product",product));
		
		_recordReader = _scon -> _rr -> {
			long	id = DImpl._get(this.id).read(_scon,_rr);
			long	invoiceId = DImpl._get(this.invoiceId).read(_scon,_rr);
			String	product = DImpl._get(this.product).read(_scon,_rr);
			return new InvoiceLine(id, invoiceId, product);
		};
	}
	public  TInvoiceLine	alias(String aliasName){
		return new TInvoiceLine(_tableContext.withAlias(aliasName));
	}
}
