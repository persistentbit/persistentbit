package com.persistentbit.sql.updater.parser;

import com.persistentbit.result.OK;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class Sql implements Statement{
	private final Text sql;


	public Sql(Text sql) {
		this.sql = sql;
	}

	@Override
	public Result<OK> run(UpdateContext context) {
		return Result.function(this).code(log -> {
			return context.executeSql(sql.toString(context));
		});
	}

	public Result<Integer>	getResultCount(UpdateContext context){
		return context.getSqlResultCount(sql.toString(context));
	}
}
