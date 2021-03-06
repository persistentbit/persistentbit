package com.persistentbit.sql.updater.parser;

import com.persistentbit.printable.PrintableText;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.string.withprops.Text;

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
			return context.executeSql(sql.toString(context::getProperty));
		});
	}

	public Result<Integer>	getResultCount(UpdateContext context){
		return context.getSqlResultCount(sql.toString(context::getProperty));
	}

	@Override
	public PrintableText print() {
		return pw-> pw.println(sql);
	}
}
