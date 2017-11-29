package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.utils.rowreader.ResultSetRowReader;
import com.persistentbit.sql.work.DbWork;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public abstract class DImplSelectionAbstract<T> extends DImpl<T> implements DbWork<PStream<T>>{
	protected final QueryImpl query;
	protected final PList<DExpr> columns;
	protected final String aliasName;
	protected final PList<DExpr> columnsWithAlias;
	public DImplSelectionAbstract(QueryImpl query,
								  PList<DExpr> columns,
								  String aliasName
	) {
		this.query = query;
		this.columns = columns;
		this.aliasName = aliasName;
		this.columnsWithAlias = columns.lazy().zipWithIndex().map(t ->
			//DImpl._get(t._2)._prefixAlias(aliasName,"v" + (t._1+1),t._2)
			t._2
		).plist();
	}

	@Override
	public SqlWithParams toSql(DbSqlContext sqlContext){
		//PStream<DExpr> wrapped = columns.lazy().zipWithIndex().map(t -> getWithAlias(t._1));
		return new SqlWithParams("SELECT ")
			.add(toSqlSelection(sqlContext))
			.add(" FROM ").add(
				query.from.map(e -> DImpl._get(e).toSqlSelectableFrom(sqlContext))
			)
			.add(query.joins.map(j -> j.toSql().nl()))
			.add(query.where == null
				? SqlWithParams.empty()
				: new SqlWithParams(" WHERE ").add(DImpl._get(query.where).toSqlSelection(sqlContext))
			)
		;
	}

	@Override
	public SqlWithParams toSqlSelection(DbSqlContext sqlContext) {
		return new SqlWithParams(columnsWithAlias.map(e -> DImpl._get(e).toSqlSelection(sqlContext)),", ");
	}

	protected  DExpr getWithAlias(int index){

		return columnsWithAlias.get(index);
	}

	@Override
	public SqlWithParams toSqlSelectableFrom(DbSqlContext context) {
		return new SqlWithParams("(").add(toSql(context))
				 .add(")" + (aliasName != null ? " AS " + aliasName : "" ));
	}

	@Override
	public String toString() {
		return toSql(query.sqlContext).toString();
	}

	@Override
	public Result<PStream<T>> run(DbTransaction transaction) {
		return transaction.run(con -> {
			SqlWithParams sqlWithParams = toSql(query.sqlContext);
			try(PreparedStatement stat = con.prepareStatement(sqlWithParams.getSql())){
				sqlWithParams.setParams(stat);
				try(ResultSet rs = stat.executeQuery()){
					PList<T>  res = PList.empty();
					ResultSetRowReader rr  = query.sqlContext.createResultSetRowReader(rs);
					while(rs.next()){
						T rec = read(query.sqlContext,rr);

						res = res.plus(rec);
						rr.nextRow();
					}
					return Result.success(res);
				}
			}
		});
	}





}
