package com.persistentbit.sql.dsl.generic.expressions.impl.jdbc;

import com.persistentbit.collections.PByteList;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class GenericExprTypeJdbcConverters{
	static public final ExprTypeJdbcConvert<Byte> forByte = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, Byte value) throws SQLException {
			if(value == null){
				stat.setNull(index,Types.TINYINT);
			}
			stat.setByte(index, value);
		}

		@Override
		public Byte read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getByte(index);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<Short> forShort = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, Short value) throws SQLException {
			if(value == null){
				stat.setNull(index,Types.SMALLINT);
			}
			stat.setShort(index, value);
		}

		@Override
		public Short read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getShort(index);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<Integer> forInt = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, Integer value) throws SQLException {
			if(value == null){
				stat.setNull(index,Types.INTEGER);
			}
			stat.setInt(index, value);
		}

		@Override
		public Integer read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getInt(index);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<Long> forLong = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, Long value) throws SQLException {
			if(value == null){
				stat.setNull(index,Types.BIGINT);
			}
			stat.setLong(index, value);
		}

		@Override
		public Long read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getLong(index);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<Float> forFloat = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, Float value) throws SQLException {
			if(value == null){
				stat.setNull(index,Types.REAL);
			}
			stat.setFloat(index, value);
		}

		@Override
		public Float read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getFloat(index);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<Double> forDouble = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, Double value) throws SQLException {
			if(value == null){
				stat.setNull(index,Types.DOUBLE);
			}
			stat.setDouble(index, value);
		}

		@Override
		public Double read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getDouble(index);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<BigDecimal> forBigDecimal = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, BigDecimal value) throws SQLException {
			if(value == null){
				stat.setNull(index,Types.NUMERIC);
			}
			stat.setBigDecimal(index, value);
		}

		@Override
		public BigDecimal read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getBigDecimal(index);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<Boolean> forBool = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, Boolean value) throws SQLException {
			if(value == null){
				stat.setNull(index,Types.BOOLEAN);
			}
			stat.setBoolean(index, value);
		}

		@Override
		public Boolean read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getBoolean(index);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};

	static public final ExprTypeJdbcConvert<String> forString = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, String value) throws SQLException {
			if(value == null){
				stat.setNull(index,Types.VARCHAR);
			}
			stat.setString(index, value);
		}

		@Override
		public String read(int index, ResultSet resultSet) throws SQLException {
			return resultSet.getString(index);
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<LocalDate> forLocalDate = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, LocalDate value) throws SQLException {
			if(value == null){
				stat.setDate(index,null);
			} else {
				stat.setDate(index, java.sql.Date.valueOf(value.atStartOfDay().toLocalDate()));
			}
		}

		@Override
		public LocalDate read(int index, ResultSet resultSet) throws SQLException {
			Date d = resultSet.getDate(index);
			return d == null ? null : d.toLocalDate();
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<LocalTime> forLocalTime = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, LocalTime value) throws SQLException {
			if(value == null){
				stat.setTime(index,null);
			} else {
				stat.setTime(index, Time.valueOf(value));
			}
		}

		@Override
		public LocalTime read(int index, ResultSet resultSet) throws SQLException {
			Time t = resultSet.getTime(index++);
			return t == null ? null : t.toLocalTime();
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};
	static public final ExprTypeJdbcConvert<PByteList> forByteList = new ExprTypeJdbcConvert<>(){
		@Override
		public void setParam(int index, PreparedStatement stat, PByteList value) throws SQLException {
			if(value == null){
				stat.setNull(index, Types.BINARY);
			}else {
				stat.setBinaryStream(index,value.getInputStream());
			}
		}

		@Override
		public PByteList read(int index, ResultSet resultSet) throws SQLException {
			byte[] bytes = resultSet.getBytes(index++);
			return (bytes == null
				? null
				: PByteList.from(bytes));
		}

		@Override
		public int columnCount() {
			return 1;
		}
	};


}
