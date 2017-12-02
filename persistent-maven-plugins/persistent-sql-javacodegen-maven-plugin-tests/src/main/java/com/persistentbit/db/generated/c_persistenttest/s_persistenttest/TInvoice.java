package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.tuples.Tuple2;

public class TInvoice extends DTable<Invoice, TInvoice> {
	public  final	DExprLong	id;
	public  final	DExprString	invoiceNummer;
	public  final	DExprLong	fromCompanyId;
	public  final	DExprLong	toCompanyId;
	
	
	public TInvoice(DbTableContext context){
		super(context);
		this.id	=	context.createExprLong(this, "id");
		this.invoiceNummer	=	context.createExprString(this, "invoice_nummer");
		this.fromCompanyId	=	context.createExprLong(this, "from_company_id");
		this.toCompanyId	=	context.createExprLong(this, "to_company_id");
		super._all = PList.val(Tuple2.of("id",id), Tuple2.of("invoiceNummer",invoiceNummer), Tuple2.of("fromCompanyId",fromCompanyId), Tuple2.of("toCompanyId",toCompanyId));
		
		_recordReader = _scon -> _rr -> {
			Long	id = DImpl._get(this.id)._read(_scon,_rr);
			String	invoiceNummer = DImpl._get(this.invoiceNummer)._read(_scon,_rr);
			Long	fromCompanyId = DImpl._get(this.fromCompanyId)._read(_scon,_rr);
			Long	toCompanyId = DImpl._get(this.toCompanyId)._read(_scon,_rr);
			if(id== null && invoiceNummer== null && fromCompanyId== null && toCompanyId== null) { return null; }
			return new Invoice(id, invoiceNummer, fromCompanyId, toCompanyId);
		};
		_doWithAlias = alias -> new TInvoice(_tableContext.withAlias(alias));
	}
	public  TInvoice	withTableAlias(String tableAlias){
		return new TInvoice(_tableContext.withTableAlias(tableAlias));
	}
	public  static TInvoice	cast(DExpr<Invoice> expr){
		return (TInvoice)expr;
	}
}
