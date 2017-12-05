package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Invoice;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import java.lang.String;

public class TInvoice extends DTableExprImpl<Invoice> {
	public  final	DExprLong	id;
	public  final	DExprString	invoiceNummer;
	public  final	DExprLong	fromCompanyId;
	public  final	DExprLong	toCompanyId;
	
	
	public TInvoice(DExprLong id, DExprString invoiceNummer, DExprLong fromCompanyId, DExprLong toCompanyId){
		super(
			PList.val(id, invoiceNummer, fromCompanyId, toCompanyId),
			_scon -> _rr -> {
				Long	_id = DImpl._get(id)._read(_scon,_rr);
				String	_invoiceNummer = DImpl._get(invoiceNummer)._read(_scon,_rr);
				Long	_fromCompanyId = DImpl._get(fromCompanyId)._read(_scon,_rr);
				Long	_toCompanyId = DImpl._get(toCompanyId)._read(_scon,_rr);
				if(_id== null && _invoiceNummer== null && _fromCompanyId== null && _toCompanyId== null) { return null; }
				return new Invoice(_id, _invoiceNummer, _fromCompanyId, _toCompanyId);
			}
		);
		this.id	=	id;
		this.invoiceNummer	=	invoiceNummer;
		this.fromCompanyId	=	fromCompanyId;
		this.toCompanyId	=	toCompanyId;
	}
	@Override
	protected  TInvoice	_doWithAlias(String alias){
		return new TInvoice(
			(DExprLong)DImpl._get(id)._withAlias(alias), 
			(DExprString)DImpl._get(invoiceNummer)._withAlias(alias), 
			(DExprLong)DImpl._get(fromCompanyId)._withAlias(alias), 
			(DExprLong)DImpl._get(toCompanyId)._withAlias(alias)
		);
	}
	public  static TInvoice	cast(DExpr<Invoice> expr){
		return (TInvoice)expr;
	}
}
