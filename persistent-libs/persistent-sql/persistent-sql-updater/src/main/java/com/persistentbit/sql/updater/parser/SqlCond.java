package com.persistentbit.sql.updater.parser;

import com.persistentbit.printable.PrintableText;
import com.persistentbit.result.Result;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class SqlCond implements Cond{
	public enum Type {
		fail,success, exists
	}

	private final Type 	type;
	private final Sql	sql;

	public SqlCond(Type type, Sql sql) {
		this.type = type;
		this.sql = sql;
	}

	@Override
	public Result<Boolean> run(UpdateContext context) {
		return Result.function(this).code(log -> {
			switch(type){
				case exists:
					return sql.getResultCount(context).map(count -> count > 0);
				case fail:
					return sql.run(context)
							  .map(ok -> false)
							  .flatMapFailure(f -> Result.success(true));

				case success:
					return sql.run(context)
							  .map(ok -> true)
							  .flatMapFailure(f -> Result.success(false));
				default:
					return Result.TODO();
			}
		});
	}

	@Override
	public PrintableText print() {
		throw new ToDo();
	}
}
