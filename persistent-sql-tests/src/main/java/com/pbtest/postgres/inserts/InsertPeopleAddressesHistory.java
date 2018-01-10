package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TPeopleAddressesHistory;
import com.pbtest.postgres.values.PeopleAddressesHistory;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InsertPeopleAddressesHistory extends Insert<TPeopleAddressesHistory, Long>{


	public InsertPeopleAddressesHistory(ExprContext context, TPeopleAddressesHistory into, PList<String> columnNames,
										PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertPeopleAddressesHistory(ExprContext context, TPeopleAddressesHistory into) {
		this(context, into, columnNames, PSet.empty(), "person_id", PList.empty());
	}

	private static final PList<String> columnNames =
		PList.val("person_id", "address_relation_code", "start_date", "end_date", "address_id");

	public InsertPeopleAddressesHistory add(@Nullable Long personId, @Nullable String addressRelationCode,
											@Nullable LocalDate startDate, @Nullable LocalDateTime endDate,
											@Nullable Long addressId) {
		Object[] row = new Object[]{
			personId
			, addressRelationCode
			, startDate
			, endDate
			, addressId
		};
		return new InsertPeopleAddressesHistory(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertPeopleAddressesHistory add(PeopleAddressesHistory value) {
		return add(
			value.getPersonId(), value.getAddressRelationCode(), value.getStartDate(), value.getEndDate()
				.orElse(null), value.getAddressId()
		);
	}
}
