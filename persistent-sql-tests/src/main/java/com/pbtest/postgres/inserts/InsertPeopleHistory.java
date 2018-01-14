package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TPeopleHistory;
import com.pbtest.postgres.values.PeopleHistory;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InsertPeopleHistory extends Insert<TPeopleHistory, Void>{


	public InsertPeopleHistory(ExprContext context, TPeopleHistory into, PList<String> columnNames,
							   PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertPeopleHistory(ExprContext context, TPeopleHistory into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList
		.val("person_id", "salutation_code", "name_first", "name_middle", "name_last", "gender_code", "birth_day", "start_time", "end_time");

	public InsertPeopleHistory add(@Nullable Long personId, @Nullable String salutationCode, @Nullable String nameFirst,
								   @Nullable String nameMiddle, @Nullable String nameLast, @Nullable String genderCode,
								   @Nullable LocalDate birthDay, @Nullable LocalDateTime startTime,
								   @Nullable LocalDateTime endTime) {
		Object[] row = new Object[]{
			personId
			, salutationCode
			, nameFirst
			, nameMiddle
			, nameLast
			, genderCode
			, birthDay
			, startTime
			, endTime
		};
		return new InsertPeopleHistory(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertPeopleHistory add(PeopleHistory value) {
		return add(
			value.getPersonId(), value.getSalutationCode(), value.getNameFirst(), value.getNameMiddle()
				.orElse(null), value.getNameLast(), value.getGenderCode(), value.getBirthDay().orElse(null), value
				.getStartTime(), value.getEndTime().orElse(null)
		);
	}
}
