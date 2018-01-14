package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EPeopleAddresses;
import com.pbtest.postgres.values.PeopleAddresses;
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

public class PeopleAddressesTypeFactory extends AbstractStructureTypeFactory<EPeopleAddresses, PeopleAddresses>{

	private class EPeopleAddressesImpl extends EPeopleAddresses
		implements StructTypeImplMixin<EPeopleAddresses, PeopleAddresses>{


		public EPeopleAddressesImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (ELong) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EPeopleAddresses, PeopleAddresses> getTypeFactory() {
			return PeopleAddressesTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EPeopleAddresses[" + personId + addressRelationCode + addressId + "]";
		}

		@Override
		public EPeopleAddresses getThis() {
			return this;
		}
	}

	public PeopleAddressesTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EPeopleAddresses, PeopleAddresses>> buildFields() {
		return PList.val(
			createField(ELong.class, "person_id", "personId", v -> v.getPersonId(), v -> v.personId)
			, createField(EString.class, "address_relation_code", "addressRelationCode", v -> v
				.getAddressRelationCode(), v -> v.addressRelationCode)
			, createField(ELong.class, "address_id", "addressId", v -> v.getAddressId(), v -> v.addressId)
		);
	}

	@Override
	protected PeopleAddresses buildValue(Object[] fieldValues) {
		return new PeopleAddresses(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (Long) fieldValues[2]
		);
	}

	@Override
	protected EPeopleAddressesImpl createExpression(PStream<DExpr> fieldValues) {
		return new EPeopleAddressesImpl(fieldValues.iterator());
	}

	@Override
	public Class<EPeopleAddresses> getTypeClass() {
		return EPeopleAddresses.class;
	}
}
