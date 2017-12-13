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
public class DefinedCond implements Cond{
	private final boolean define;
	private final String name;

	public DefinedCond(boolean define, String name) {
		this.define = define;
		this.name = name;
	}

	@Override
	public Result<Boolean> run(UpdateContext context) {
		return Result.success(context.getProperty(name).isPresent() == define);
	}
	@Override
	public PrintableText print() {
		throw new ToDo();
	}
}
