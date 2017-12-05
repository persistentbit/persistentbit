package com.persistentbit.sql.dsl.generic.inserts;

import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImplTable;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.work.DbWork;

import java.sql.PreparedStatement;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/12/17
 */
public class Insert<T> implements DbWork<Integer>{
	private final DbContext	dbContext;
	private final DExprTable<T> into;
	private final PList<DExpr<T>> rows;


	public Insert(DbContext dbContext, DExprTable<T> into,
				  PList<DExpr<T>> rows
	) {
		this.dbContext = dbContext;
		this.into = into;
		this.rows = rows;
	}

	public Insert(DbContext dbContext, DExprTable<T> into, DExpr<T>...rows){
		this(dbContext,into,PList.val(rows));
	}


	private SqlWithParams toSql(DbSqlContext sqlContext){
		SqlWithParams res = SqlWithParams.sql("INSERT INTO ");
		DImplTable impl = DImplTable._get(into);
		res = res.add(impl._toSqlFrom(sqlContext));
		res = res.add("(").add(impl._getInsertList(sqlContext)).add(")").add(SqlWithParams.nl);
		res = res.add(rows.map(r -> DImpl._get(r)._toSqlValues(sqlContext)),System.lineSeparator());
		return res;
	}

	@Override
	public String toString() {
		return toSql(dbContext.createSqlContext()).toString();
	}

	@Override
	public Result<Integer> run(DbTransaction transaction) {
		return transaction.run(con -> {
			return Result.function().code(l -> {
				SqlWithParams sqlWithParams = toSql(dbContext.createSqlContext());
				String        sql           = sqlWithParams.getSql();
				l.info("Executing " + sqlWithParams);
				l.info("Executing " + sql);
				try(PreparedStatement stat = con.prepareStatement(sql)){
					sqlWithParams.setParams(stat);
					int count = stat.executeUpdate();
					return Result.success(count);
					/*try(){
						PList<T>           res    = PList.empty();
						ResultSetRowReader rr     = sqlContext.createResultSetRowReader(rs);
						DInternal<T>       reader = DImpl._get(columns);
						while(rs.next()){
							T rec = reader._read(sqlContext,rr);

							res = res.plus(rec);
							rr.nextRow();
						}
						return Result.success(res);
					}*/
				}
			});


		});
	}
	/*
	public Result<Integer> execute(DbContext dbc, DbTransManager tm) throws Exception {
		return Result.function(new Object[]{dbc, tm}).code((log) -> {
			InsertSqlBuilder b = new InsertSqlBuilder(dbc, this);
			log.info(b.generateNoParams());
			Tuple2<String, Consumer<PreparedStatement>> generatedQuery = b.generate();
			PreparedStatement                           s              = tm.get().prepareStatement((String)generatedQuery._1);
			Throwable                                   var7           = null;

			Success var8;
			try {
				((Consumer)generatedQuery._2).accept(s);
				var8 = Result.success(s.executeUpdate());
			} catch (Throwable var17) {
				var7 = var17;
				throw var17;
			} finally {
				if (s != null) {
					if (var7 != null) {
						try {
							s.close();
						} catch (Throwable var16) {
							var7.addSuppressed(var16);
						}
					} else {
						s.close();
					}
				}

			}

			return var8;
		});
	}*/

}
