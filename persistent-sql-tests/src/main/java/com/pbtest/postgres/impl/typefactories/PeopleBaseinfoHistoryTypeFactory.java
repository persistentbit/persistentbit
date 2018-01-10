package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EPeopleBaseinfoHistory;
import com.pbtest.postgres.values.PeopleBaseinfoHistory;
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

public class PeopleBaseinfoHistoryTypeFactory
	extends AbstractStructureTypeFactory<EPeopleBaseinfoHistory, PeopleBaseinfoHistory>{

	private class EPeopleBaseinfoHistoryImpl extends EPeopleBaseinfoHistory
		implements StructTypeImplMixin<EPeopleBaseinfoHistory, PeopleBaseinfoHistory>{


		public EPeopleBaseinfoHistoryImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EDateTime) iter.next()
				, (EDateTime) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EDate) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EPeopleBaseinfoHistory, PeopleBaseinfoHistory> getTypeFactory() {
			return PeopleBaseinfoHistoryTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EPeopleBaseinfoHistory[" + personId + startTime + endTime + salutationCode + nameFirst + nameMiddle + nameLast + genderCode + birthDay + "]";
		}

		@Override
		public EPeopleBaseinfoHistory getThis() {
			return this;
		}
	}

	public PeopleBaseinfoHistoryTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EPeopleBaseinfoHistory, PeopleBaseinfoHistory>> buildFields() {
		return PList.val(
			createField(ELong.class, "person_id", "personId", v -> v.getPersonId(), v -> v.personId)
			, createField(EDateTime.class, "start_time", "startTime", v -> v.getStartTime(), v -> v.startTime)
			, createField(EDateTime.class, "end_time", "endTime", v -> v.getEndTime().orElse(null), v -> v.endTime)
			, createField(EString.class, "salutation_code", "salutationCode", v -> v
				.getSalutationCode(), v -> v.salutationCode)
			, createField(EString.class, "name_first", "nameFirst", v -> v.getNameFirst(), v -> v.nameFirst)
			, createField(EString.class, "name_middle", "nameMiddle", v -> v.getNameMiddle()
				.orElse(null), v -> v.nameMiddle)
			, createField(EString.class, "name_last", "nameLast", v -> v.getNameLast(), v -> v.nameLast)
			, createField(EString.class, "gender_code", "genderCode", v -> v.getGenderCode(), v -> v.genderCode)
			, createField(EDate.class, "birth_day", "birthDay", v -> v.getBirthDay().orElse(null), v -> v.birthDay)
		);
	}

	@Override
	protected PeopleBaseinfoHistory buildValue(Object[] fieldValues) {
		return new PeopleBaseinfoHistory(
			(Long) fieldValues[0]
			, (LocalDateTime) fieldValues[1]
			, (LocalDateTime) fieldValues[2]
			, (String) fieldValues[3]
			, (String) fieldValues[4]
			, (String) fieldValues[5]
			, (String) fieldValues[6]
			, (String) fieldValues[7]
			, (LocalDate) fieldValues[8]
		);
	}

	@Override
	protected EPeopleBaseinfoHistoryImpl createExpression(PStream<DExpr> fieldValues) {
		return new EPeopleBaseinfoHistoryImpl(fieldValues.iterator());
	}

	@Override
	public Class<EPeopleBaseinfoHistory> getTypeClass() {
		return EPeopleBaseinfoHistory.class;
	}
}
