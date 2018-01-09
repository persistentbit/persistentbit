package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EAddresses;
import com.pbtest.postgres.values.Addresses;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class AddressesTypeFactory extends AbstractStructureTypeFactory<EAddresses, Addresses>{

	private class EAddressesImpl extends EAddresses implements StructTypeImplMixin<EAddresses, Addresses>{


		public EAddressesImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EObject) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EAddresses, Addresses> getTypeFactory() {
			return AddressesTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EAddresses[" + addressId + streetLine1 + streetLine2 + postalCode + cityName + countryCode + district + "]";
		}

		@Override
		public EAddresses getThis() {
			return this;
		}
	}

	public AddressesTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EAddresses, Addresses>> buildFields() {
		return PList.val(
			createField(ELong.class, "address_id", "addressId", v -> v.getAddressId(), v -> v.addressId)
			, createField(EString.class, "street_line_1", "streetLine1", v -> v.getStreetLine1(), v -> v.streetLine1)
			, createField(EString.class, "street_line_2", "streetLine2", v -> v.getStreetLine2()
				.orElse(null), v -> v.streetLine2)
			, createField(EString.class, "postal_code", "postalCode", v -> v.getPostalCode(), v -> v.postalCode)
			, createField(EString.class, "city_name", "cityName", v -> v.getCityName(), v -> v.cityName)
			, createField(EObject.class, "country_code", "countryCode", v -> v.getCountryCode(), v -> v.countryCode)
			, createField(EString.class, "district", "district", v -> v.getDistrict().orElse(null), v -> v.district)
		);
	}

	@Override
	protected Addresses buildValue(Object[] fieldValues) {
		return new Addresses(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
			, (String) fieldValues[3]
			, (String) fieldValues[4]
			, (Object) fieldValues[5]
			, (String) fieldValues[6]
		);
	}

	@Override
	protected EAddressesImpl createExpression(PStream<DExpr> fieldValues) {
		return new EAddressesImpl(fieldValues.iterator());
	}

	@Override
	public Class<EAddresses> getTypeClass() {
		return EAddresses.class;
	}
}
