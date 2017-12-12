package com.persistentbit.sql.updater.parser;

import com.persistentbit.collections.PList;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class StatementList implements Statement{
	private final PList<Statement> statements;

	public StatementList(PList<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public Result<OK> run(UpdateContext context) {
		return Result.function().code(log -> {
			for(Statement stat : statements){
				Result<OK> statRes = log.add(stat.run(context));
				if(statRes.isError()){
					return statRes;
				}
			}
			return OK.result;
		});

	}
}
