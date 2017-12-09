package com.persistentbit.sql.work;

import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.logging.FunctionLogging;
import com.persistentbit.logging.entries.LogContext;
import com.persistentbit.logging.entries.LogEntryFunction;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.tuples.Tuple2;

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

	static  <T> DbWork<T> emptyWork(String message){
		return trans -> Result.empty(message);
	}

	static <T> DbWork<T> defaultWork(T value){
		return trans -> Result.result(value);
	}

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

	default <MAPT> DbWork<MAPT> map(Function<R,MAPT> mapper){
		DbWork<R> self = this;

		return DbWork.function().code(trans -> con -> l ->
			self.run(trans)
				.map(v -> mapper.apply(v))
		);
	}

	default <MAPT> DbWork<MAPT> flatMap(Function<R,Result<MAPT>> mapper){
		DbWork<R> self = this;
		return DbWork.function().code(trans -> con -> l ->
			self.run(trans).flatMap(v -> mapper.apply(v))
		);
	}

	default <OTHER> DbWork<Tuple2<R, OTHER>> combine(Function<R, DbWork<OTHER>> other) {
		return trans -> {
			Result<R> resR = run(trans);
			if(resR.isPresent() == false) {
				return resR.map(v -> null); //Map error
			}
			R r = resR.orElseThrow();
			return other.apply(r).run(trans)
						.map(o -> Tuple2.of(r, o));
		};
	}

	static DbWork<OK> sequence(Iterable<DbWork<OK>> sequence) {
		return DbWork.function().code(trans -> con -> log ->   {
			for(DbWork<OK> w : sequence) {
				Result<OK> itemOK = w.run(trans);
				if(itemOK.isError()) {
					return itemOK;
				}
				log.add(itemOK);
			}
			return OK.result;
		});
	}

	static FLogging function() {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		LogEntryFunction  fe  = LogEntryFunction.of(new LogContext(ste));
		return new FLogging(fe);
	}

	static FLogging function(Object... params) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		LogEntryFunction  fe  = LogEntryFunction.of(new LogContext(ste));
		FLogging          res = new FLogging(fe);
		res.params(params);
		return res;
	}


	class FLogging extends FunctionLogging{

		public FLogging(LogEntryFunction lef, int stackEntryIndex) {
			super(lef, stackEntryIndex);
		}

		public FLogging(LogEntryFunction lef) {
			this(lef, 2);
		}



		@SuppressWarnings("unchecked")
		public <R> DbWork<R> code(Function<DbTransaction, Function<Connection,ThrowingFunction<FLogging,Result<R>,Exception>>> code) {
			return trans -> trans.run( con -> {
				Result<R> result;
				try{
					result = code.apply(trans).apply(con).apply(this);
				}catch(Exception e){
					result = Result.failure(e);
				}
				return result.mapLog(l -> getLog().append(l));
			});
		}

	}
}
