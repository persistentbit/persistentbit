package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TInvoice;
import com.mycompany.db.generated.values.Invoice;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertInvoice extends Insert<TInvoice, Long>{


	public InsertInvoice(ExprContext context, TInvoice into, PList<String> columnNames, PSet<String> withDefaults,
						 String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertInvoice(ExprContext context, TInvoice into) {
		this(context, into, columnNames, PSet.empty(), "id", PList.empty());
	}

	private static final PList<String> columnNames =
		PList.val("id", "invoice_nummer", "from_company_id", "to_company_id");

	public InsertInvoice add(@Nullable Long id, @Nullable String invoiceNummer, @Nullable Long fromCompanyId,
							 @Nullable Long toCompanyId) {
		Object[] row = new Object[]{
			id
			, invoiceNummer
			, fromCompanyId
			, toCompanyId
		};
		return new InsertInvoice(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertInvoice add(Invoice value) {
		return add(
			value.getId(), value.getInvoiceNummer(), value.getFromCompanyId(), value.getToCompanyId()
		);
	}
}
