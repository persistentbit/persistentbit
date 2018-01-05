package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EAPerson;
import com.mycompany.db.generated.values.APerson;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class APersonTypeFactory extends AbstractStructureTypeFactory<EAPerson, APerson>{

	private class EAPersonImpl extends EAPerson implements StructTypeImplMixin<EAPerson, APerson>{


		public EAPersonImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EInt) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EAPerson, APerson> getTypeFactory() {
			return APersonTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EAPerson[" + id + userName + password + street + houseNumber + busNumber + postalcode + city + country + "]";
		}
		@Override
		public EAPerson getThis() {
			return this;
		}
	}

	public APersonTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EAPerson, APerson>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(EString.class, "user_name", "userName", v -> v.getUserName(), v -> v.userName)
			, createField(EString.class, "password", "password", v -> v.getPassword(), v -> v.password)
			, createField(EString.class, "street", "street", v -> v.getStreet(), v -> v.street)
			, createField(EInt.class, "house_number", "houseNumber", v -> v.getHouseNumber(), v -> v.houseNumber)
			, createField(EString.class, "bus_number", "busNumber", v -> v.getBusNumber()
				.orElse(null), v -> v.busNumber)
			, createField(EString.class, "postalcode", "postalcode", v -> v.getPostalcode(), v -> v.postalcode)
			, createField(EString.class, "city", "city", v -> v.getCity(), v -> v.city)
			, createField(EString.class, "country", "country", v -> v.getCountry(), v -> v.country)
		);
	}
	@Override
	protected APerson buildValue(Object[] fieldValues) {
		return new APerson(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
			, (String) fieldValues[3]
			, (Integer) fieldValues[4]
			, (String) fieldValues[5]
			, (String) fieldValues[6]
			, (String) fieldValues[7]
			, (String) fieldValues[8]
		);
	}
	@Override
	protected EAPersonImpl createExpression(PStream<DExpr> fieldValues) {
		return new EAPersonImpl(fieldValues.iterator());
	}
	@Override
	public Class<EAPerson> getTypeClass() {
		return EAPerson.class;
	}
}
