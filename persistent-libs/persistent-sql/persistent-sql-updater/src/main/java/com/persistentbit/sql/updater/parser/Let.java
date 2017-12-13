package com.persistentbit.sql.updater.parser;

import com.persistentbit.printable.PrintableText;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.string.withprops.Text;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class Let implements Statement{
	private final String propertyName;
	private final Text value;

	public Let(String propertyName, Text value) {
		this.propertyName = propertyName;
		this.value = value;
	}

	@Override
	public Result<OK> run(UpdateContext context) {
		return Result.function(this).code(log -> {
			context.setProperty(propertyName,value.toString(context::getProperty));
			return OK.result;
		});
	}

	@Override
	public PrintableText print() {
		throw new ToDo();
	}
}
