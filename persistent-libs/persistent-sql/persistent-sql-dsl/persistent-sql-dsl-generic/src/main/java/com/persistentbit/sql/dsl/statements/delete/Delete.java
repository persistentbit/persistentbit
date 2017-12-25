package com.persistentbit.sql.dsl.statements.delete;

import com.persistentbit.collections.PMap;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.work.DbWork;

import java.sql.PreparedStatement;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class Delete{

	private final ExprContext context;
	private final Table       table;


	public Delete(ExprContext context, Table table) {
		this.context = context;
		this.table = table;
	}


	public DbWork<Integer> all() {
		return DbWork.function().code(trans -> con -> log -> {
			String sql = "DELETE FROM " + context.getFromTableName(table);
			log.info("Exec SQL:" + sql);
			return Result.success(con.createStatement().executeUpdate(sql));
		});
	}

	public DbWork<Integer> where(EBool where) {
		return DbWork.function().code(trans -> con -> log -> {
			SqlWithParams sql =
				SqlWithParams.sql("DELETE FROM " + context.getFromTableName(table) + " WHERE ")
					.add(context.toSql(where));
			log.info("Exec SQL:" + sql);

			try(PreparedStatement stat = con.prepareStatement(sql.getSql())) {
				sql.setParams(PMap.empty(), stat);
				return Result.success(stat.executeUpdate());
			}
		});
	}



}
