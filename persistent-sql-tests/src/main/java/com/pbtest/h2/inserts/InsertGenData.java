package com.pbtest.h2.inserts;

import com.pbtest.h2.tables.TGenData;
import com.pbtest.h2.values.GenData;
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
		.val("GEN_DATA_ID", "A_INT", "A_SHORT", "A_LONG", "A_NUM", "A_DOUBLE", "A_REAL", "A_BOOL", "A_DATE", "A_TIME", "A_TIMESTAMP", "A_STRING");

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
