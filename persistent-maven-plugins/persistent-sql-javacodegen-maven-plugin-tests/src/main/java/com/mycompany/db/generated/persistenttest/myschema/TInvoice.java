package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.ELong;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.EString;

import java.lang.String;

public class TInvoice extends DTableExprImpl<Invoice> {
	public  final ELong   id;
	public  final EString invoiceNummer;
	public  final ELong   fromCompanyId;
	public  final ELong   toCompanyId;
	
	
	public TInvoice(ELong id, EString invoiceNummer, ELong fromCompanyId, ELong toCompanyId){
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
			(ELong)DImpl._get(id)._withAlias(alias),
			(EString)DImpl._get(invoiceNummer)._withAlias(alias),
			(ELong)DImpl._get(fromCompanyId)._withAlias(alias),
			(ELong)DImpl._get(toCompanyId)._withAlias(alias)
		);
	}
	public  static TInvoice	cast(DExpr<Invoice> expr){
		return (TInvoice)expr;
	}
}
