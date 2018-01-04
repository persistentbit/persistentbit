package com.persistentbit.sql.dsl.genericdb;

import com.persistentbit.collections.PByteList;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class GenericExprTypeJdbcConverters{

	static public final ExprTypeJdbcConvert<Byte> forByte = new ExprTypeJdbcConvert.SingleColumnImpl<Byte>(
		Types.TINYINT, "byte", j -> j, o -> (Byte) o
	);


	static public final ExprTypeJdbcConvert<Short> forShort = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.SMALLINT, "smallint", j -> j, o -> (Short) o
	);

	static public final ExprTypeJdbcConvert<Integer> forInt = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.INTEGER, "integer", j -> j, o -> (Integer) o
	);

	static public final ExprTypeJdbcConvert<Long> forLong = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.BIGINT, "bigint", j -> j, o -> (Long) o
	);

	static public final ExprTypeJdbcConvert<Float> forFloat = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.FLOAT, "real", j -> j, o -> (Float) o
	);

	static public final ExprTypeJdbcConvert<Double> forDouble = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.DOUBLE, "double precision", j -> j, o -> (Double) o
	);

	static public final ExprTypeJdbcConvert<BigDecimal> forBigDecimal = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.NUMERIC, "numeric", j -> j, o -> (BigDecimal) o
	);

	static public final ExprTypeJdbcConvert<Boolean> forBool = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.BOOLEAN, "boolean", j -> j, o -> (Boolean) o
	);


	static public final ExprTypeJdbcConvert<String> forString = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.VARCHAR, "text", j -> j, o -> (String) o
	);

	static public final ExprTypeJdbcConvert<LocalDate> forLocalDate = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.DATE, "date"
		, j -> j == null ? null : Date.valueOf(j.atStartOfDay().toLocalDate())
		, o -> o == null ? null : ((Date) o).toLocalDate()
	);


	static public final ExprTypeJdbcConvert<LocalTime> forLocalTime = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.TIME, "time"
		, j -> j == null ? null : Time.valueOf(j)
		, o -> o == null ? null : ((Time) o).toLocalTime()
	);

	static public final ExprTypeJdbcConvert<LocalDateTime> forLocalDateTime =
		new ExprTypeJdbcConvert.SingleColumnImpl<>(
			Types.TIMESTAMP, "time"
			, j -> j == null ? null : Timestamp.valueOf(j)
			, o -> o == null ? null : ((Timestamp) o).toLocalDateTime()
		);


	static public final ExprTypeJdbcConvert<PByteList> forByteList = new ExprTypeJdbcConvert.SingleColumnImpl<>(
		Types.BINARY, "bytea"
		, j -> j == null ? null : j.toByteArray()
		, o -> o == null ? null : PByteList.from((byte[]) o)
	);


}
