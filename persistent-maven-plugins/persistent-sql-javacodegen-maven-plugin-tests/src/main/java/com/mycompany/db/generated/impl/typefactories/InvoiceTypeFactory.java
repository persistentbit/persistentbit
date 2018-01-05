package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EInvoice;
import com.mycompany.db.generated.values.Invoice;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class InvoiceTypeFactory extends AbstractStructureTypeFactory<EInvoice, Invoice>{

	private class EInvoiceImpl extends EInvoice implements StructTypeImplMixin<EInvoice, Invoice>{


		public EInvoiceImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (ELong) iter.next()
				, (ELong) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EInvoice, Invoice> getTypeFactory() {
			return InvoiceTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EInvoice[" + id + invoiceNummer + fromCompanyId + toCompanyId + "]";
		}
		@Override
		public EInvoice getThis() {
			return this;
		}
	}

	public InvoiceTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EInvoice, Invoice>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(EString.class, "invoice_nummer", "invoiceNummer", v -> v
				.getInvoiceNummer(), v -> v.invoiceNummer)
			, createField(ELong.class, "from_company_id", "fromCompanyId", v -> v
				.getFromCompanyId(), v -> v.fromCompanyId)
			, createField(ELong.class, "to_company_id", "toCompanyId", v -> v.getToCompanyId(), v -> v.toCompanyId)
		);
	}
	@Override
	protected Invoice buildValue(Object[] fieldValues) {
		return new Invoice(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (Long) fieldValues[2]
			, (Long) fieldValues[3]
		);
	}
	@Override
	protected EInvoiceImpl createExpression(PStream<DExpr> fieldValues) {
		return new EInvoiceImpl(fieldValues.iterator());
	}
	@Override
	public Class<EInvoice> getTypeClass() {
		return EInvoice.class;
	}
}
