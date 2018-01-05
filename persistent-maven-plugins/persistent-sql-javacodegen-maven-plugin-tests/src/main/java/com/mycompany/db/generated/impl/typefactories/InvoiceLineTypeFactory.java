package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EInvoiceLine;
import com.mycompany.db.generated.values.InvoiceLine;
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

public class InvoiceLineTypeFactory extends AbstractStructureTypeFactory<EInvoiceLine, InvoiceLine>{

	private class EInvoiceLineImpl extends EInvoiceLine implements StructTypeImplMixin<EInvoiceLine, InvoiceLine>{


		public EInvoiceLineImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (ELong) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EInvoiceLine, InvoiceLine> getTypeFactory() {
			return InvoiceLineTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EInvoiceLine[" + id + invoiceId + product + "]";
		}

		@Override
		public EInvoiceLine getThis() {
			return this;
		}
	}

	public InvoiceLineTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EInvoiceLine, InvoiceLine>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(ELong.class, "invoice_id", "invoiceId", v -> v.getInvoiceId(), v -> v.invoiceId)
			, createField(EString.class, "product", "product", v -> v.getProduct().orElse(null), v -> v.product)
		);
	}

	@Override
	protected InvoiceLine buildValue(Object[] fieldValues) {
		return new InvoiceLine(
			(Long) fieldValues[0]
			, (Long) fieldValues[1]
			, (String) fieldValues[2]
		);
	}

	@Override
	protected EInvoiceLineImpl createExpression(PStream<DExpr> fieldValues) {
		return new EInvoiceLineImpl(fieldValues.iterator());
	}

	@Override
	public Class<EInvoiceLine> getTypeClass() {
		return EInvoiceLine.class;
	}
}
