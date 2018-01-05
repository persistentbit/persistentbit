package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TAllGenericNulls;
import com.mycompany.db.generated.values.AllGenericNulls;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PByteList;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class InsertAllGenericNulls extends Insert<TAllGenericNulls, Void>{


	public InsertAllGenericNulls(ExprContext context, TAllGenericNulls into, PList<String> columnNames,
								 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertAllGenericNulls(ExprContext context, TAllGenericNulls into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList
		.val("id_part1", "id_part2", "ser_small", "ser", "ser_big", "an_integer", "a_bigint", "a_decimal_7_2", "a_numeric_6", "a_numeric", "a_real", "a_double", "an_int2", "an_int4", "an_int8", "a_varchar", "a_varchar_10", "a_text", "a_char", "a_char_10", "a_bytea", "a_timestamp_3", "a_timestamp", "a_timestamp_with_zone", "a_date", "a_time", "a_time_with_zone", "a_boolean");

	public InsertAllGenericNulls add(@Nullable String idPart1, @Nullable Long idPart2, @Nullable Short serSmall,
									 @Nullable Integer ser, @Nullable Long serBig, @Nullable Integer anInteger,
									 @Nullable Long aBigint, @Nullable BigDecimal aDecimal72,
									 @Nullable BigDecimal aNumeric6, @Nullable BigDecimal aNumeric,
									 @Nullable Float aReal, @Nullable Double aDouble, @Nullable Short anInt2,
									 @Nullable Integer anInt4, @Nullable Long anInt8, @Nullable String aVarchar,
									 @Nullable String aVarchar10, @Nullable String aText, @Nullable String aChar,
									 @Nullable String aChar10, @Nullable PByteList aBytea,
									 @Nullable LocalDateTime aTimestamp3, @Nullable LocalDateTime aTimestamp,
									 @Nullable LocalDateTime aTimestampWithZone, @Nullable LocalDate aDate,
									 @Nullable LocalTime aTime, @Nullable LocalTime aTimeWithZone,
									 @Nullable Boolean aBoolean) {
		Object[] row = new Object[]{
			idPart1
			, idPart2
			, serSmall
			, ser
			, serBig
			, anInteger
			, aBigint
			, aDecimal72
			, aNumeric6
			, aNumeric
			, aReal
			, aDouble
			, anInt2
			, anInt4
			, anInt8
			, aVarchar
			, aVarchar10
			, aText
			, aChar
			, aChar10
			, aBytea
			, aTimestamp3
			, aTimestamp
			, aTimestampWithZone
			, aDate
			, aTime
			, aTimeWithZone
			, aBoolean
		};
		return new InsertAllGenericNulls(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertAllGenericNulls add(AllGenericNulls value) {
		return add(
			value.getIdPart1(), value.getIdPart2(), value.getSerSmall(), value.getSer(), value.getSerBig(), value
				.getAnInteger().orElse(null), value.getABigint().orElse(null), value.getADecimal72().orElse(null), value
				.getANumeric6().orElse(null), value.getANumeric().orElse(null), value.getAReal().orElse(null), value
				.getADouble().orElse(null), value.getAnInt2().orElse(null), value.getAnInt4().orElse(null), value
				.getAnInt8().orElse(null), value.getAVarchar().orElse(null), value.getAVarchar10().orElse(null), value
				.getAText().orElse(null), value.getAChar().orElse(null), value.getAChar10().orElse(null), value
				.getABytea().orElse(null), value.getATimestamp3().orElse(null), value.getATimestamp()
				.orElse(null), value.getATimestampWithZone().orElse(null), value.getADate().orElse(null), value
				.getATime().orElse(null), value.getATimeWithZone().orElse(null), value.getABoolean().orElse(null)
		);
	}
}
