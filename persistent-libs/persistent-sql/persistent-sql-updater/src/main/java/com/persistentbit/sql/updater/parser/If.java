package com.persistentbit.sql.updater.parser;


import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.parser.source.StrPos;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class If implements Statement{
	private final StrPos pos;
	private final Cond	cond;
	private final Statement	trueStatement;
	@Nullable
	private final Statement falseStatement;

	public If(StrPos pos, Cond cond, Statement trueStatement, Statement falseStatement) {
		this.pos = pos;
		this.cond = cond;
		this.trueStatement = trueStatement;
		this.falseStatement = falseStatement;
	}

	@Override
	public Result<OK> run(UpdateContext context) {
		return Result.function(this).code(log ->
			cond.run(context)
				.flatMap(isTrue ->  isTrue
					? trueStatement.run(context)
					: (falseStatement == null ? OK.result : falseStatement.run(context))
				)
		);
	}
}
