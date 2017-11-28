package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Invoice;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.tuples.Tuple2;

public class TInvoice extends DTable<Invoice> {
	public  final	DExprLong	id;
	public  final	DExprString	invoiceNummer;
	public  final	DExprLong	fromCompanyId;
	public  final	DExprLong	toCompanyId;
	
	
	public TInvoice(DbTableContext context){
		super(context);
		this.id	=	context.createExprLong(this, "id");
		this.invoiceNummer	=	context.createExprString(this, "invoiceNummer");
		this.fromCompanyId	=	context.createExprLong(this, "fromCompanyId");
		this.toCompanyId	=	context.createExprLong(this, "toCompanyId");
		super._all = PList.val(Tuple2.of("id",id), Tuple2.of("invoiceNummer",invoiceNummer), Tuple2.of("fromCompanyId",fromCompanyId), Tuple2.of("toCompanyId",toCompanyId));
	}
}
