package com.persistentbit.sql.work;

import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.result.Result;
import com.persistentbit.sql.transactions.DbTransaction;

import java.sql.Connection;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/11/17
 */
@FunctionalInterface
public interface DbWork<R>{

	Result<R>	run(DbTransaction transaction);

	static <T> DbWork<T> create(Function<DbTransaction, ThrowingFunction<Connection, Result<T>, Exception>> code){
		return trans -> trans.run( con -> Result.function().code(l -> {
			Result<T> result;
			try{
				result = code.apply(trans).apply(con);
			}catch(Exception e){
				result = Result.failure(e);
			}
			return result;
		}));
	}


}
