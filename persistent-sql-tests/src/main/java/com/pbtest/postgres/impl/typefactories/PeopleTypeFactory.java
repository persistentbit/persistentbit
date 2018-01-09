package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EPeople;
import com.pbtest.postgres.values.People;
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

public class PeopleTypeFactory extends AbstractStructureTypeFactory<EPeople, People>{

	private class EPeopleImpl extends EPeople implements StructTypeImplMixin<EPeople, People>{


		public EPeopleImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EObject) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EObject) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EPeople, People> getTypeFactory() {
			return PeopleTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EPeople[" + personId + salutationCode + nameFirst + nameMiddle + nameLast + genderCode + "]";
		}

		@Override
		public EPeople getThis() {
			return this;
		}
	}

	public PeopleTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EPeople, People>> buildFields() {
		return PList.val(
			createField(ELong.class, "person_id", "personId", v -> v.getPersonId(), v -> v.personId)
			, createField(EObject.class, "salutation_code", "salutationCode", v -> v
				.getSalutationCode(), v -> v.salutationCode)
			, createField(EString.class, "name_first", "nameFirst", v -> v.getNameFirst(), v -> v.nameFirst)
			, createField(EString.class, "name_middle", "nameMiddle", v -> v.getNameMiddle()
				.orElse(null), v -> v.nameMiddle)
			, createField(EString.class, "name_last", "nameLast", v -> v.getNameLast(), v -> v.nameLast)
			, createField(EObject.class, "gender_code", "genderCode", v -> v.getGenderCode(), v -> v.genderCode)
		);
	}

	@Override
	protected People buildValue(Object[] fieldValues) {
		return new People(
			(Long) fieldValues[0]
			, (Object) fieldValues[1]
			, (String) fieldValues[2]
			, (String) fieldValues[3]
			, (String) fieldValues[4]
			, (Object) fieldValues[5]
		);
	}

	@Override
	protected EPeopleImpl createExpression(PStream<DExpr> fieldValues) {
		return new EPeopleImpl(fieldValues.iterator());
	}

	@Override
	public Class<EPeople> getTypeClass() {
		return EPeople.class;
	}
}
