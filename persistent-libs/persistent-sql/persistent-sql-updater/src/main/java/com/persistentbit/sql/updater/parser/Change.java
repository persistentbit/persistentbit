package com.persistentbit.sql.updater.parser;

import com.persistentbit.parser.source.StrPos;
import com.persistentbit.printable.PrintableText;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class Change{
	private final StrPos pos;
	private final boolean always;
	private final String name;
	private final String author;
	private final Statement statement;

	public Change(StrPos pos, boolean always, String name, String author,
				  Statement statement
	) {
		this.pos = pos;
		this.always = always;
		this.name = name;
		this.author = author;
		this.statement = statement;
	}
	public ChangeResult	run(UpdateContext context){
		Result<OK> allResult = Result.function().code(log -> {
			if(context.isChangeNeeded(name,author) == false){
				log.info("Skipped change " + name + " by " + author + ": not needed");
				return Result.empty("Change not needed");
			}
			return statement.run(context);
		});


		return new ChangeResult(this,allResult);
	}
	public PrintableText	print(){
		return pw -> {
			pw.println("Change " + (always ? "always " : "") + name + " by " + author);
			pw.indent(statement.print());
		};
	}
}
