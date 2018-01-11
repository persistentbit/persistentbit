package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TPeopleAddressesHistory;
import com.pbtest.postgres.values.PeopleAddressesHistory;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.time.LocalDateTime;

public class InsertPeopleAddressesHistory extends Insert<TPeopleAddressesHistory, Void>{


	public InsertPeopleAddressesHistory(ExprContext context, TPeopleAddressesHistory into, PList<String> columnNames,
										PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertPeopleAddressesHistory(ExprContext context, TPeopleAddressesHistory into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames =
		PList.val("person_id", "address_relation_code", "address_id", "start_time", "end_time");

	public InsertPeopleAddressesHistory add(@Nullable Long personId, @Nullable String addressRelationCode,
											@Nullable Long addressId, @Nullable LocalDateTime startTime,
											@Nullable LocalDateTime endTime) {
		Object[] row = new Object[]{
			personId
			, addressRelationCode
			, addressId
			, startTime
			, endTime
		};
		return new InsertPeopleAddressesHistory(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertPeopleAddressesHistory add(PeopleAddressesHistory value) {
		return add(
			value.getPersonId(), value.getAddressRelationCode(), value.getAddressId(), value.getStartTime(), value
				.getEndTime().orElse(null)
		);
	}
}
