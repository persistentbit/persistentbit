package com.persistentbit.sql.dsl.generic.expressions.impl.jdbc;

import com.persistentbit.collections.PList;
import com.persistentbit.functions.ThrowingFunction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	int columnCount();



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
			public int columnCount() {
				return count;
			}
		};
	}
	static <J> ExprTypeJdbcConvert<J> createMultiColumn(PList<ExprTypeJdbcConvert> group,
														Function<Object[],J> toValue){
		int count = group.map(g -> g.columnCount()).fold(0,(a,b) -> a+b);
		return new ExprTypeJdbcConvert<>(){
			@Override
			public void setParam(int index, PreparedStatement stat, J value) throws SQLException {

			}

			@Override
			public J read(int index, ResultSet resultSet) throws SQLException {
				int i = 0;
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
		};
	}
}
