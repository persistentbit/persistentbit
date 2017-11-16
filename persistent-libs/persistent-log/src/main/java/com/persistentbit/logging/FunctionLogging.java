package com.persistentbit.logging;


import com.persistentbit.logging.entries.LogEntry;
import com.persistentbit.logging.entries.LogEntryFunction;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/17
 */
public class FunctionLogging extends AbstractLogEntryLogging {

	@FunctionalInterface
	public interface LoggedFunction<R>{
		R run(FunctionLogging l) throws Exception;
	}

	protected LogEntryFunction entry;


	public FunctionLogging(LogEntryFunction entry, int stackEntryIndex) {
		super(stackEntryIndex);
		this.entry = entry;
	}

	public LogEntryFunction getLog(){
		return this.entry;
	}
	public Void add(LogEntry logEntry){
		if(logEntry != null){
			map(entry -> entry.append(logEntry) );
		}
		return null;
	}



	protected void map(Function<LogEntryFunction,LogEntryFunction> mapper){
		entry = mapper.apply(entry);
	}
	public void functionDoneTimestamp(long timestamp){
		map(e -> e.withTimestampDone(timestamp));
	}
	public void functionResult(Object result){
		map(e -> e.withResultValue(objectToString(result)));
	}



	public FunctionLogging params(Object...params){
		map(e -> e.withParamsString(objectsToString(params)));
		return this;
	}




}
