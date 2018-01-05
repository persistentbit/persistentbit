package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.Invoice;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EInvoice implements DExpr<Invoice>{

	public final ELong   id;
	public final EString invoiceNummer;
	public final ELong   fromCompanyId;
	public final ELong   toCompanyId;


	public EInvoice(ELong id, EString invoiceNummer, ELong fromCompanyId, ELong toCompanyId) {
		this.id = id;
		this.invoiceNummer = invoiceNummer;
		this.fromCompanyId = fromCompanyId;
		this.toCompanyId = toCompanyId;
	}
}
