package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;
import com.mycompany.db.generated.persistenttest.myschema.InvoiceLine;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import java.lang.String;

public class TInvoiceLine extends DTableExprImpl<InvoiceLine> {
	public  final	DExprLong	id;
	public  final	DExprLong	invoiceId;
	public  final	DExprString	product;
	
	
	public TInvoiceLine(DExprLong id, DExprLong invoiceId, DExprString product){
		super(
			PList.val(id, invoiceId, product),
			_scon -> _rr -> {
				Long	_id = DImpl._get(id)._read(_scon,_rr);
				Long	_invoiceId = DImpl._get(invoiceId)._read(_scon,_rr);
				String	_product = DImpl._get(product)._read(_scon,_rr);
				if(_id== null && _invoiceId== null && _product== null) { return null; }
				return new InvoiceLine(_id, _invoiceId, _product);
			}
		);
		this.id	=	id;
		this.invoiceId	=	invoiceId;
		this.product	=	product;
	}
	@Override
	protected  TInvoiceLine	_doWithAlias(String alias){
		return new TInvoiceLine(
			(DExprLong)DImpl._get(id)._withAlias(alias), 
			(DExprLong)DImpl._get(invoiceId)._withAlias(alias), 
			(DExprString)DImpl._get(product)._withAlias(alias)
		);
	}
	public  static TInvoiceLine	cast(DExpr<InvoiceLine> expr){
		return (TInvoiceLine)expr;
	}
}
