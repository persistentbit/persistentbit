package com.persistentbit.sql.dsl.genericdb;

import com.persistentbit.collections.PByteList;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;

import java.math.BigDecimal;
import java.sql.*;
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


	static public final ExprTypeJdbcConvert<String>        forString        = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, String value) throws SQLException {
			if(value == null) {
				stat.setNull(index, Types.VARCHAR);
				return;
			}
			stat.setString(index, value);
		}

		@Override
		public String read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getString(index);
		}

		@Override
		public PList<ExprTypeJdbcConvert> expand() {
			return PList.val(this);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<LocalDate>     forLocalDate     = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, LocalDate value) throws SQLException {
			if(value == null) {
				stat.setNull(index, Types.DATE);
			}
			else {
				stat.setDate(index, Date.valueOf(value.atStartOfDay().toLocalDate()));
			}
		}

		@Override
		public LocalDate read(int index, ResultSet resultSet) throws SQLException {
			Date d = resultSet.getDate(index);
			return d == null ? null : d.toLocalDate();
		}

		@Override
		public PList<ExprTypeJdbcConvert> expand() {
			return PList.val(this);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<LocalTime>     forLocalTime     = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, LocalTime value) throws SQLException {
			if(value == null) {
				stat.setNull(index, Types.TIME);
			}
			else {
				stat.setTime(index, Time.valueOf(value));
			}
		}

		@Override
		public LocalTime read(int index, ResultSet resultSet) throws SQLException {
			Time t = resultSet.getTime(index);
			return t == null ? null : t.toLocalTime();
		}

		@Override
		public PList<ExprTypeJdbcConvert> expand() {
			return PList.val(this);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<LocalDateTime> forLocalDateTime = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, LocalDateTime value) throws SQLException {
			if(value == null) {
				stat.setNull(index, Types.TIMESTAMP);
			}
			else {
				stat.setTimestamp(index, Timestamp.valueOf(value));
			}
		}

		@Override
		public LocalDateTime read(int index, ResultSet resultSet) throws SQLException {
			Timestamp d = resultSet.getTimestamp(index);
			return d == null ? null : d.toLocalDateTime();
		}

		@Override
		public PList<ExprTypeJdbcConvert> expand() {
			return PList.val(this);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<PByteList>     forByteList      = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, PByteList value) throws SQLException {
			if(value == null) {
				stat.setNull(index, Types.BINARY);
			}
			else {
				stat.setBinaryStream(index, value.getInputStream());
			}
		}

		@Override
		public PByteList read(int index, ResultSet resultSet) throws SQLException {
			byte[] bytes = resultSet.getBytes(index);
			return (bytes == null
				? null
				: PByteList.from(bytes));
		}

		@Override
		public PList<ExprTypeJdbcConvert> expand() {
			return PList.val(this);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};


}
