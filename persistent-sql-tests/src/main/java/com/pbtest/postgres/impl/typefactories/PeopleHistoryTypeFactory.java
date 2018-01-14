package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EPeopleHistory;
import com.pbtest.postgres.values.PeopleHistory;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;

public class PeopleHistoryTypeFactory extends AbstractStructureTypeFactory<EPeopleHistory, PeopleHistory>{

	private class EPeopleHistoryImpl extends EPeopleHistory
		implements StructTypeImplMixin<EPeopleHistory, PeopleHistory>{


		public EPeopleHistoryImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EDate) iter.next()
				, (EDateTime) iter.next()
				, (EDateTime) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EPeopleHistory, PeopleHistory> getTypeFactory() {
			return PeopleHistoryTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EPeopleHistory[" + personId + salutationCode + nameFirst + nameMiddle + nameLast + genderCode + birthDay + startTime + endTime + "]";
		}

		@Override
		public EPeopleHistory getThis() {
			return this;
		}
	}

	public PeopleHistoryTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EPeopleHistory, PeopleHistory>> buildFields() {
		return PList.val(
			createField(ELong.class, "person_id", "personId", v -> v.getPersonId(), v -> v.personId)
			, createField(EString.class, "salutation_code", "salutationCode", v -> v
				.getSalutationCode(), v -> v.salutationCode)
			, createField(EString.class, "name_first", "nameFirst", v -> v.getNameFirst(), v -> v.nameFirst)
			, createField(EString.class, "name_middle", "nameMiddle", v -> v.getNameMiddle()
				.orElse(null), v -> v.nameMiddle)
			, createField(EString.class, "name_last", "nameLast", v -> v.getNameLast(), v -> v.nameLast)
			, createField(EString.class, "gender_code", "genderCode", v -> v.getGenderCode(), v -> v.genderCode)
			, createField(EDate.class, "birth_day", "birthDay", v -> v.getBirthDay().orElse(null), v -> v.birthDay)
			, createField(EDateTime.class, "start_time", "startTime", v -> v.getStartTime(), v -> v.startTime)
			, createField(EDateTime.class, "end_time", "endTime", v -> v.getEndTime().orElse(null), v -> v.endTime)
		);
	}

	@Override
	protected PeopleHistory buildValue(Object[] fieldValues) {
		return new PeopleHistory(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
			, (String) fieldValues[3]
			, (String) fieldValues[4]
			, (String) fieldValues[5]
			, (LocalDate) fieldValues[6]
			, (LocalDateTime) fieldValues[7]
			, (LocalDateTime) fieldValues[8]
		);
	}

	@Override
	protected EPeopleHistoryImpl createExpression(PStream<DExpr> fieldValues) {
		return new EPeopleHistoryImpl(fieldValues.iterator());
	}

	@Override
	public Class<EPeopleHistory> getTypeClass() {
		return EPeopleHistory.class;
	}
}
