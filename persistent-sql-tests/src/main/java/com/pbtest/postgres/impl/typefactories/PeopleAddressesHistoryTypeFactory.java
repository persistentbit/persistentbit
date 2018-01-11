package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EPeopleAddressesHistory;
import com.pbtest.postgres.values.PeopleAddressesHistory;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

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
				, (ELong) iter.next()
				, (EDateTime) iter.next()
				, (EDateTime) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EPeopleAddressesHistory, PeopleAddressesHistory> getTypeFactory() {
			return PeopleAddressesHistoryTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EPeopleAddressesHistory[" + personId + addressRelationCode + addressId + startTime + endTime + "]";
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
			, createField(ELong.class, "address_id", "addressId", v -> v.getAddressId(), v -> v.addressId)
			, createField(EDateTime.class, "start_time", "startTime", v -> v.getStartTime(), v -> v.startTime)
			, createField(EDateTime.class, "end_time", "endTime", v -> v.getEndTime().orElse(null), v -> v.endTime)
		);
	}
	@Override
	protected PeopleAddressesHistory buildValue(Object[] fieldValues) {
		return new PeopleAddressesHistory(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (Long) fieldValues[2]
			, (LocalDateTime) fieldValues[3]
			, (LocalDateTime) fieldValues[4]
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
