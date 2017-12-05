package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class SqlWithParams{

	static private abstract class Item {
		abstract int setParams(PreparedStatement stat, int index) throws SQLException;
		abstract String toSqlString();
	}

	private final PList<Item> items;

	private SqlWithParams(PList<Item> items) {
		this.items = items;
	}


	private static class ParamItem extends Item{
		private final PrepStatParam prepStatParam;
		public ParamItem(PrepStatParam prepStatParam){
			this.prepStatParam = prepStatParam;
		}

		@Override
		public String toString() {
			return "?(" + prepStatParam+")";
		}

		@Override
		int setParams(PreparedStatement stat, int index) throws SQLException{
			prepStatParam._setPrepStatement(stat, index);
			return index+1;
		}

		@Override
		String toSqlString() {
			return "?";
		}
	}
	private static class StringItem extends Item{
		private final String sql;
		public StringItem(String sql){
			this.sql = sql;
		}

		@Override
		public String toString() {
			return sql;
		}

		@Override
		int setParams(PreparedStatement stat, int index)  {
			return index;
		}

		@Override
		String toSqlString() {
			return sql;
		}
	}

	static public final SqlWithParams empty = new SqlWithParams(PList.empty());
	static public final SqlWithParams space = empty.add(" ");
	static public final SqlWithParams nl = empty.add(System.lineSeparator());

	static public  SqlWithParams sql(String sql){
		return SqlWithParams.empty.add(sql);
	}
	static public SqlWithParams param(PrepStatParam param){
		return SqlWithParams.empty.add(param);
	}

	public SqlWithParams add(SqlWithParams other){
		if(this.items.isEmpty()){
			return other;
		}
		return new SqlWithParams(items.plusAll(other.items));
	}

	public SqlWithParams add(String sql){
		if(sql.isEmpty()){
			return this;
		}
		return new SqlWithParams(items.plus(new StringItem(sql)));
	}

	public SqlWithParams add(PrepStatParam param){
		return new SqlWithParams(items.plus(new ParamItem(param)));
	}

	public SqlWithParams add(PStream<SqlWithParams> collection, String sep){
		//return collection.fold(this, (a,b) -> a.add(sep).add(b));
		if(collection.isEmpty()){
			return this;
		}
		return add(collection.tail().fold(collection.headOpt().get(),(a,b)-> a.add(sep).add(b)));

	}



	@Override
	public String toString() {
		return items.map(i ->i.toString()).fold("",(a,b)-> a + b);
	}

	public void setParams(PreparedStatement stat) throws SQLException {
		int t = 1;
		for(Item i : items){
			t = i.setParams(stat,t);
		}
	}
	public String getSql() {
		return items.map(i -> i.toSqlString()).fold("",(a,b)-> a + b);
	}


	/*
	private final String sql;
	private final PList<PrepStatParam> params;

	public SqlWithParams(String sql,
						 PList<PrepStatParam> params
	) {
		this.sql = sql;
		this.params = params;
	}
	public SqlWithParams(PStream<SqlWithParams> collection, String sep){
		sql = collection.map(s -> s.sql).toString(sep);
		params = collection.map(s -> s.params).fold(PList.empty(),(a,b)-> a.plusAll(b));
	}
	public SqlWithParams(String sql){
		this(sql,PList.empty());
	}
	public SqlWithParams(PrepStatParam param){
		this("?", PList.val(param));
	}

	static SqlWithParams empty() {
		return new SqlWithParams("");
	}

	public SqlWithParams add(SqlWithParams other){
		return new SqlWithParams(this.sql+other.sql, this.params.plusAll(other.params));
	}
	public SqlWithParams add(PStream<SqlWithParams> collection){
		return add(collection," ");
	}
	public SqlWithParams add(PStream<SqlWithParams> collection, String sqlSep){
		if(collection.isEmpty()){
			return this;
		}
		return add(collection.tail().fold(collection.headOpt().get(),(a,b)-> a.add(sqlSep).add(b)));
	}
	public SqlWithParams add(String sql){
		return add(new SqlWithParams(sql));
	}
	public SqlWithParams add(PrepStatParam param){
		return add(new SqlWithParams(param));
	}

	public SqlWithParams space() {
		return add(" ");
	}
	public SqlWithParams nl() {
		return add(System.lineSeparator());
	}

	@Override
	public String toString() {
		return nl().sql + "Params: " + params.toString(", ");
	}

	void setParams(PreparedStatement stat) throws SQLException{
		int t = 1;
		for(PrepStatParam p : params){
			p._setPrepStatement(stat, t++);
		}
	}
	String getSql() {
		return sql;
	}
	*/
}
