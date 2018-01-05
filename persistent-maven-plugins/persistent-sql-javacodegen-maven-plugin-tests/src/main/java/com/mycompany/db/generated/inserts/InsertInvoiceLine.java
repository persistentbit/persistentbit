package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TInvoiceLine;
import com.mycompany.db.generated.values.InvoiceLine;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertInvoiceLine extends Insert<TInvoiceLine, Long>{


	public InsertInvoiceLine(ExprContext context, TInvoiceLine into, PList<String> columnNames,
							 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertInvoiceLine(ExprContext context, TInvoiceLine into) {
		this(context, into, columnNames, PSet.empty(), "id", PList.empty());
	}

	private static final PList<String> columnNames = PList.val("id", "invoice_id", "product");

	public InsertInvoiceLine add(@Nullable Long id, @Nullable Long invoiceId, @Nullable String product) {
		Object[] row = new Object[]{
			id
			, invoiceId
			, product
		};
		return new InsertInvoiceLine(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertInvoiceLine add(InvoiceLine value) {
		return add(
			value.getId(), value.getInvoiceId(), value.getProduct().orElse(null)
		);
	}
}
