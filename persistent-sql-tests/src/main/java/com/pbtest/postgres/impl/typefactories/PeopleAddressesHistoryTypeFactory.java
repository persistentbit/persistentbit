package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EPeopleAddressesHistory;
import com.pbtest.postgres.values.PeopleAddressesHistory;
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

public class PeopleAddressesHistoryTypeFactory
	extends AbstractStructureTypeFactory<EPeopleAddressesHistory, PeopleAddressesHistory>{

	private class EPeopleAddressesHistoryImpl extends EPeopleAddressesHistory
		implements StructTypeImplMixin<EPeopleAddressesHistory, PeopleAddressesHistory>{


		public EPeopleAddressesHistoryImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (EDate) iter.next()
				, (EDateTime) iter.next()
				, (ELong) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EPeopleAddressesHistory, PeopleAddressesHistory> getTypeFactory() {
			return PeopleAddressesHistoryTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EPeopleAddressesHistory[" + personId + addressRelationCode + startDate + endDate + addressId + "]";
		}
		@Override
		public EPeopleAddressesHistory getThis() {
			return this;
		}
	}

	public PeopleAddressesHistoryTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EPeopleAddressesHistory, PeopleAddressesHistory>> buildFields() {
		return PList.val(
			createField(ELong.class, "person_id", "personId", v -> v.getPersonId(), v -> v.personId)
			, createField(EString.class, "address_relation_code", "addressRelationCode", v -> v
				.getAddressRelationCode(), v -> v.addressRelationCode)
			, createField(EDate.class, "start_date", "startDate", v -> v.getStartDate(), v -> v.startDate)
			, createField(EDateTime.class, "end_date", "endDate", v -> v.getEndDate().orElse(null), v -> v.endDate)
			, createField(ELong.class, "address_id", "addressId", v -> v.getAddressId(), v -> v.addressId)
		);
	}
	@Override
	protected PeopleAddressesHistory buildValue(Object[] fieldValues) {
		return new PeopleAddressesHistory(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (LocalDate) fieldValues[2]
			, (LocalDateTime) fieldValues[3]
			, (Long) fieldValues[4]
		);
	}
	@Override
	protected EPeopleAddressesHistoryImpl createExpression(PStream<DExpr> fieldValues) {
		return new EPeopleAddressesHistoryImpl(fieldValues.iterator());
	}
	@Override
	public Class<EPeopleAddressesHistory> getTypeClass() {
		return EPeopleAddressesHistory.class;
	}
}
