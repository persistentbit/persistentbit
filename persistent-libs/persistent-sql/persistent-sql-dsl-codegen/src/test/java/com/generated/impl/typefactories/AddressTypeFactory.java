package com.generated.impl.typefactories;

import com.generated.expressions.EAddress;
import com.generated.values.Address;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class AddressTypeFactory extends AbstractStructureTypeFactory<EAddress, Address>{

	private class EAddressImpl extends EAddress implements ExprTypeImpl<EAddress, Address>{


		public EAddressImpl(Iterator<DExpr> iter) {
			super(
				(EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
			);
		}
		@Override
		public ExprTypeFactory<EAddress, Address> getTypeFactory() {
			return AddressTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EAddress[" + street + postalCode + city + "]";
		}
	}

	public AddressTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EAddress, Address>> buildFields() {
		return PList.val(
			createField(EString.class, "street", "street", v -> v.getStreet(), v -> v.street)
			, createField(EString.class, "postal_code", "postalCode", v -> v.getPostalCode(), v -> v.postalCode)
			, createField(EString.class, "city", "city", v -> v.getCity(), v -> v.city)
		);
	}
	@Override
	protected Address buildValue(Object[] fieldValues) {
		return new Address(
			(String) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
		);
	}
	@Override
	protected EAddressImpl createExpression(PStream<DExpr> fieldValues) {
		return new EAddressImpl(fieldValues.iterator());
	}
	@Override
	public Class<? extends DExpr<Address>> getTypeClass() {
		return EAddress.class;
	}
}
