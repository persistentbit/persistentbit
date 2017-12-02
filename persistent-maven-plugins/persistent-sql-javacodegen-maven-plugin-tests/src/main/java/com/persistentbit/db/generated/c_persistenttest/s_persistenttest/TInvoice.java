package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Invoice;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.tuples.Tuple2;
import java.lang.String;

public class TInvoice extends DTable<Invoice> {
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
			long	id = DImpl._get(this.id).read(_scon,_rr);
			String	invoiceNummer = DImpl._get(this.invoiceNummer).read(_scon,_rr);
			long	fromCompanyId = DImpl._get(this.fromCompanyId).read(_scon,_rr);
			long	toCompanyId = DImpl._get(this.toCompanyId).read(_scon,_rr);
			return new Invoice(id, invoiceNummer, fromCompanyId, toCompanyId);
		};
	}
	public  TInvoice	alias(String aliasName){
		return new TInvoice(_tableContext.withAlias(aliasName));
	}
}
