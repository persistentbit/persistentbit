package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.DInternal;
import com.persistentbit.sql.dsl.generic.expressions.impl.dtable.DImplTable;
import com.persistentbit.sql.dsl.generic.query.DSelectionTable;
import com.persistentbit.sql.dsl.generic.query.Selection;
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
public class SelectionImpl<T> implements Selection<T>,DbWork<PStream<T>>{
	final QueryImpl query;
	final DExpr<T> columns;
	public SelectionImpl(QueryImpl query,
						 DExpr<T> columns
	) {
		this.query = query;
		this.columns = columns;
	}


	public SqlWithParams toSql(DbSqlContext sqlContext){
		return toSql(sqlContext,null);
	}

	SqlWithParams toSql(DbSqlContext sqlContext, String alias){

		SqlWithParams selection = DImpl._get(columns)._toSqlSelection(sqlContext,alias);

		SqlWithParams from = SqlWithParams.empty.add(query.from.map(e -> DImplTable._get(e)._toSqlFrom(sqlContext)),", ");

		SqlWithParams joins = SqlWithParams.empty.add(query.joins.map(j -> j.toSql(sqlContext)),System.lineSeparator());
		return SqlWithParams.sql("SELECT ")
			.add(selection)
			.add(" FROM ").add(from)
			.add(joins)
			.add(query.where == null
				? SqlWithParams.empty
				: SqlWithParams.sql(System.lineSeparator() + " WHERE ").add(DImpl._get(query.where)._toSql(sqlContext))
			)
		;
	}


/*
	@Override
	public SqlWithParams toSqlSelectableFrom(DbSqlContext context) {
		return new SqlWithParams("(").add(_toSql(context))
				 .add(")" + (aliasName != null ? " AS " + aliasName : "" ));
	}
	*/

	@Override
	public String toString() {
		return toSql(query.dbContext.createSqlContext()).toString();
	}

	@Override
	public Result<PStream<T>> run(DbTransaction transaction) {
		return transaction.run(con -> {
			DbSqlContext sqlContext = query.dbContext.createSqlContext();
			SqlWithParams sqlWithParams = toSql(sqlContext);
			String sql = sqlWithParams.getSql();
			try(PreparedStatement stat = con.prepareStatement(sql)){
				sqlWithParams.setParams(stat);
				try(ResultSet rs = stat.executeQuery()){
					PList<T>           res    = PList.empty();
					ResultSetRowReader rr     = sqlContext.createResultSetRowReader(rs);
					DInternal<T>       reader = DImpl._get(columns);
					while(rs.next()){
						T rec = reader._read(sqlContext,rr);

						res = res.plus(rec);
						rr.nextRow();
					}
					return Result.success(res);
				}
			}
		});
	}

	@Override
	public DSelectionTable<T> asTableExpr(String aliasName) {
		return new SelectionAsTableImpl<>(this,aliasName);
	}
}
