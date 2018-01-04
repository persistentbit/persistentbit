package com.persistentbit.sql.dsl.expressions.impl.jdbc;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;

import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface ExprTypeJdbcConvert<J>{


	void setParam(int index, PreparedStatement stat, J value) throws SQLException;
	J read(int index, ResultSet resultSet) throws SQLException;

	Array createJdbcArray(Connection con, ImmutableArray<J> values) throws SQLException;

	ImmutableArray<J> createJavaArray(Array jdbcArray);

	int columnCount();

	PList<ExprTypeJdbcConvert> expand();


	class SingleColumnImpl<J> implements ExprTypeJdbcConvert<J>{

		private final int                 jdbcType;
		private final String              arrayType;
		private final Function<J, Object> toJdbc;
		private final Function<Object, J> toJava;


		public SingleColumnImpl(int jdbcType, String arrayType, Function<J, Object> toJdbc, Function<Object, J> toJava
		) {
			this.jdbcType = jdbcType;
			this.toJava = toJava;
			this.toJdbc = toJdbc;
			this.arrayType = arrayType;
		}

		@Override
		public void setParam(int index, PreparedStatement stat, J value) throws SQLException {
			Object jdbcValue = toJdbc.apply(value);
			if(jdbcValue == null) {
				stat.setNull(index, jdbcType);
			}
			else {
				stat.setObject(index, jdbcValue, jdbcType);
			}
		}

		@Override
		public J read(int index, ResultSet resultSet) throws SQLException {
			return toJava.apply(resultSet.getObject(index));
		}

		@Override
		public Array createJdbcArray(Connection con, ImmutableArray<J> values) throws SQLException {
			if(values == null) {
				return null;
			}
			Object[] jdbcValues = values.map(v -> toJdbc.apply(v)).toArray(Object.class);
			return con.createArrayOf(arrayType, jdbcValues);
		}

		@Override
		public ImmutableArray<J> createJavaArray(Array jdbcArray) throws SQLException {
			if(jdbcArray == null) {
				return null;
			}
			ArrayList<J> res = new ArrayList<>();
			try(ResultSet rs = jdbcArray.getResultSet()) {
				while(rs.next()) {
					res.add(toJava.apply(rs.getObject(2)));
				}
			}
			return ImmutableArray.from(res);
		}

		@Override
		public int columnCount() {
			return 1;
		}

		@Override
		public PList<ExprTypeJdbcConvert> expand() {
			return PList.val(this);
		}
	}

	/*
		static <J> ExprTypeJdbcConvert<J> createSingleColumn(int count,
			Function<Integer,Function<PreparedStatement,ThrowingFunction<J,Object,SQLException>>> setParam,
			Function<Integer,ThrowingFunction<ResultSet,J,SQLException>> readResultSet
		 ){
			return new ExprTypeJdbcConvert<>(){
				@Override
				public void setParam(int index, PreparedStatement stat, J value) throws SQLException {
					setParam.apply(index).apply(stat).apply(value);
				}

				@Override
				public J read(int index, ResultSet resultSet) throws SQLException {
					return readResultSet.apply(index).apply(resultSet);
				}

				@Override
				public void setArrayParam(int index, PreparedStatement stat, ImmutableArray<J> array) throws SQLException {

				}

				@Override
				public ImmutableArray<J> readArray(int index, ResultSet resultSet) throws SQLException {
					return null;
				}

				@Override
				public int columnCount() {
					return count;
				}

				@Override
				public PList<ExprTypeJdbcConvert> expand() {
					return PList.val(this);
				}
			};
		}*/
	static <J> ExprTypeJdbcConvert<J> createMultiColumn(PList<ExprTypeJdbcConvert> group,
														Function<Object[],J> toValue){
		int count = group.map(g -> g.columnCount()).fold(0, (a, b) -> a+b);
		return new ExprTypeJdbcConvert<>(){
			@Override
			public void setParam(int index, PreparedStatement stat, J value) throws SQLException {

			}

			@Override
			public J read(int index, ResultSet resultSet) throws SQLException {
				int      i   = 0;
				Object[] res = new Object[count];
				for(ExprTypeJdbcConvert item : group){
					res[i++] = item.read(index,resultSet);
					index += item.columnCount();
				}
				return toValue.apply(res);
			}

			@Override
			public int columnCount() {
				return count;
			}

			@Override
			public PList<ExprTypeJdbcConvert> expand() {
				return group.map(j -> j.expand()).<ExprTypeJdbcConvert>flatten().plist();
			}
		};
	}
}
