package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class SqlWithParams{
	private final String sql;
	private final PList<PrepStatParam> params;

	public SqlWithParams(String sql,
						 PList<PrepStatParam> params
	) {
		this.sql = sql;
		this.params = params;
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
}
