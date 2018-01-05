package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.InvoiceLine;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EInvoiceLine implements DExpr<InvoiceLine>{

	public final ELong   id;
	public final ELong   invoiceId;
	public final EString product;


	public EInvoiceLine(ELong id, ELong invoiceId, EString product) {
		this.id = id;
		this.invoiceId = invoiceId;
		this.product = product;
	}
}
