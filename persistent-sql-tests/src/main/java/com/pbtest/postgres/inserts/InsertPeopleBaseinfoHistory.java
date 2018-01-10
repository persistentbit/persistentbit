package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TPeopleBaseinfoHistory;
import com.pbtest.postgres.values.PeopleBaseinfoHistory;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InsertPeopleBaseinfoHistory extends Insert<TPeopleBaseinfoHistory, Long>{


	public InsertPeopleBaseinfoHistory(ExprContext context, TPeopleBaseinfoHistory into, PList<String> columnNames,
									   PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertPeopleBaseinfoHistory(ExprContext context, TPeopleBaseinfoHistory into) {
		this(context, into, columnNames, PSet.empty(), "person_id", PList.empty());
	}

	private static final PList<String> columnNames = PList
		.val("person_id", "start_time", "end_time", "salutation_code", "name_first", "name_middle", "name_last", "gender_code", "birth_day");

	public InsertPeopleBaseinfoHistory add(@Nullable Long personId, @Nullable LocalDateTime startTime,
										   @Nullable LocalDateTime endTime, @Nullable String salutationCode,
										   @Nullable String nameFirst, @Nullable String nameMiddle,
										   @Nullable String nameLast, @Nullable String genderCode,
										   @Nullable LocalDate birthDay) {
		Object[] row = new Object[]{
			personId
			, startTime
			, endTime
			, salutationCode
			, nameFirst
			, nameMiddle
			, nameLast
			, genderCode
			, birthDay
		};
		return new InsertPeopleBaseinfoHistory(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertPeopleBaseinfoHistory add(PeopleBaseinfoHistory value) {
		return add(
			value.getPersonId(), value.getStartTime(), value.getEndTime().orElse(null), value.getSalutationCode(), value
				.getNameFirst(), value.getNameMiddle().orElse(null), value.getNameLast(), value.getGenderCode(), value
				.getBirthDay().orElse(null)
		);
	}
}
