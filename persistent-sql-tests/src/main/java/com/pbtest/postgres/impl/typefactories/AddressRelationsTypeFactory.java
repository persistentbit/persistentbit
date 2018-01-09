package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EAddressRelations;
import com.pbtest.postgres.values.AddressRelations;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class AddressRelationsTypeFactory extends AbstractStructureTypeFactory<EAddressRelations, AddressRelations>{

	private class EAddressRelationsImpl extends EAddressRelations
		implements StructTypeImplMixin<EAddressRelations, AddressRelations>{


		public EAddressRelationsImpl(Iterator<DExpr> iter) {
			super(
				(EObject) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EAddressRelations, AddressRelations> getTypeFactory() {
			return AddressRelationsTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EAddressRelations[" + addressRelationCode + description + "]";
		}

		@Override
		public EAddressRelations getThis() {
			return this;
		}
	}

	public AddressRelationsTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EAddressRelations, AddressRelations>> buildFields() {
		return PList.val(
			createField(EObject.class, "address_relation_code", "addressRelationCode", v -> v
				.getAddressRelationCode(), v -> v.addressRelationCode)
			, createField(EString.class, "description", "description", v -> v.getDescription(), v -> v.description)
		);
	}

	@Override
	protected AddressRelations buildValue(Object[] fieldValues) {
		return new AddressRelations(
			(Object) fieldValues[0]
			, (String) fieldValues[1]
		);
	}

	@Override
	protected EAddressRelationsImpl createExpression(PStream<DExpr> fieldValues) {
		return new EAddressRelationsImpl(fieldValues.iterator());
	}

	@Override
	public Class<EAddressRelations> getTypeClass() {
		return EAddressRelations.class;
	}
}
