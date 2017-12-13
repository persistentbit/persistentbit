package com.persistentbit.sql.updater.parser;

import com.persistentbit.printable.PrintableText;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class Include implements Statement{

	@Override
	public Result<OK> run(UpdateContext context
	) {
		return Result.TODO();
	}

	@Override
	public PrintableText print() {
		throw new ToDo();
	}
}
