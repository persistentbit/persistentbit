package com.generated.mydb.myschema;

import com.generated.catalog.schema.Address;
import com.generated.catalog.schema.EAddress;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.time.LocalDateTime;
import java.util.Iterator;

public class PersonsTypeFactory extends AbstractStructureTypeFactory<EPersons, Persons>{

	private class EPersonsImpl extends EPersons implements ExprTypeImpl<EPersons, Persons>{


		public EPersonsImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EAddress) iter.next()
				, (EDateTime) iter.next()
			);
		}

		@Override
		public ExprTypeFactory<EPersons, Persons> getTypeFactory() {
			return PersonsTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EPersons[" + personId + firstName + middleName + lastName + home + created + "]";
		}
	}

	public PersonsTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EPersons, Persons>> buildFields() {
		return PList.val(
			createField(ELong.class, "person_id", "personId", v -> v.getPersonId(), v -> v.personId)
			, createField(EString.class, "first_name", "firstName", v -> v.getFirstName(), v -> v.firstName)
			, createField(EString.class, "middle_name", "middleName", v -> v.getMiddleName()
				.orElse(null), v -> v.middleName)
			, createField(EString.class, "last_Name", "lastName", v -> v.getLastName(), v -> v.lastName)
			, createField(EAddress.class, "home_", "home", v -> v.getHome(), v -> v.home)
			, createField(EDateTime.class, "created", "created", v -> v.getCreated(), v -> v.created)
		);
	}

	@Override
	protected Persons buildValue(Object[] fieldValues) {
		return new Persons(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
			, (String) fieldValues[3]
			, (Address) fieldValues[4]
			, (LocalDateTime) fieldValues[5]
		);
	}

	@Override
	protected EPersonsImpl createExpression(PStream<DExpr> fieldValues) {
		return new EPersonsImpl(fieldValues.iterator());
	}

	@Override
	public Class<? extends DExpr<Persons>> getTypeClass() {
		return EPersons.class;
	}
}
