package com.generated.impl.typefactories;

import com.generated.expressions.EAddress;
import com.generated.expressions.EPersons;
import com.generated.values.Address;
import com.generated.values.Persons;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
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
		public EPersons buildAlias(String alias) {
			return null;
		}

		@Override
		public EPersons buildSelection(String prefixAlias) {
			return null;
		}

		@Override
		public EPersons onlyTableColumn() {
			return null;
		}

		@Override
		public PList<DExpr> expand() {
			return null;
		}

		@Override
		public SqlWithParams toSql() {
			return null;
		}

		@Override
		public ExprTypeJdbcConvert<Persons> getJdbcConverter() {
			return null;
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
	public Class<EPersons> getTypeClass() {
		return EPersons.class;
	}
}
