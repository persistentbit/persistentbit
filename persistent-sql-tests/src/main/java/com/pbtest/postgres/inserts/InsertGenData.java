package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TGenData;
import com.pbtest.postgres.values.GenData;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class InsertGenData extends Insert<TGenData, Void>{


	public InsertGenData(ExprContext context, TGenData into, PList<String> columnNames, PSet<String> withDefaults,
						 String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertGenData(ExprContext context, TGenData into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList
		.val("gen_data_id", "a_int", "a_short", "a_long", "a_num", "a_double", "a_real", "a_bool", "a_date", "a_time", "a_timestamp", "a_string");

	public InsertGenData add(@Nullable Integer genDataId, @Nullable Integer aInt, @Nullable Short aShort,
							 @Nullable Long aLong, @Nullable BigDecimal aNum, @Nullable Double aDouble,
							 @Nullable Float aReal, @Nullable Boolean aBool, @Nullable LocalDate aDate,
							 @Nullable LocalTime aTime, @Nullable LocalDateTime aTimestamp, @Nullable String aString) {
		Object[] row = new Object[]{
			genDataId
			, aInt
			, aShort
			, aLong
			, aNum
			, aDouble
			, aReal
			, aBool
			, aDate
			, aTime
			, aTimestamp
			, aString
		};
		return new InsertGenData(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertGenData add(GenData value) {
		return add(
			value.getGenDataId(), value.getAInt(), value.getAShort(), value.getALong(), value.getANum(), value
				.getADouble(), value.getAReal(), value.getABool(), value.getADate(), value.getATime(), value
				.getATimestamp(), value.getAString()
		);
	}
}
