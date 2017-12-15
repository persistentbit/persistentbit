package com.persistentbit.parser;

import com.persistentbit.logging.entries.*;
import com.persistentbit.parser.source.Source;
import com.persistentbit.result.Result;

import java.util.Objects;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/02/17
 */
public abstract class ParseResult<T>{
	protected final LogEntry logEntry;


	private ParseResult(LogEntry logEntry){
		this.logEntry = logEntry;
	}

	public static <R> ParseFailure<R> failure(Source source, String errorMessage) {
		return new ParseFailure<>(source, new ParseException(errorMessage, source.position));
	}

	public static <R> ParseFailure<R> failure(Source source, ParseException parseException) {
		return new ParseFailure<>(source, parseException);
	}

	public static <R> ParseSuccess<R> success(Source source, R value) {
		return new ParseSuccess<>(source, value);
	}

	public abstract T getValue();

	public abstract ParseException getError();

	public abstract Source getSource();

	public abstract <U> U match(
		Function<ParseSuccess<T>,U> success,
		Function<ParseFailure<T>,U> failure
	);

	public abstract boolean isSuccess();
	public boolean isFailure() {
		return !isSuccess();
	}

	public abstract <R> ParseResult<R> map(Function<T, R> mapper);

	public abstract ParseResult<T> mapSource(Function<Source, Source> sourceMapper);

	public abstract ParseResult<T> onErrorAdd(String errorMessage);

	public abstract ParseResult<T> onErrorAdd(ParseException exception);

	public abstract ParseResult<T> log(LogEntry logEntry);

	public ParseResult<T> info(String message){
		return log(new LogEntryMessage(
			LogMessageLevel.info,
			new LogContext(Thread.currentThread().getStackTrace()[1]),
			message
		));
	}
	public ParseResult<T> warning(String message){
		return log(new LogEntryMessage(
			LogMessageLevel.info,
			new LogContext(Thread.currentThread().getStackTrace()[1]),
			message
		));
	}


	public Result<T> asResult() {
		if(isSuccess()) {
			return Result.success(getValue());
		}
		return Result.failure(getError());
	}

	public LogEntry getLog() {
		return logEntry;
	}

	public static class ParseSuccess<T> extends ParseResult<T>{

		private final Source source;
		private final T value;
		public ParseSuccess(Source source, T value, LogEntry log) {
			super(log);
			this.source = Objects.requireNonNull(source);
			this.value = Objects.requireNonNull(value);
		}

		public ParseSuccess(Source source, T value) {
			this(source,value,LogEntryEmpty.inst);
		}

		@Override
		public boolean isSuccess() {
			return true;
		}

		public Source getSource() {
			return source;
		}

		@Override
		public <U> U match(Function<ParseSuccess<T>, U> success,
						   Function<ParseFailure<T>, U> failure
		) {
			return success.apply(this);
		}

		@Override
		public <R> ParseResult<R> map(Function<T, R> mapper) {
			try {
				R r = mapper.apply(value);
				return new ParseSuccess<>(source, r);
			}catch(Exception e){
				return ParseResult.failure(source, new ParseException("Mapper failed",e,source.position));
			}

		}

		@Override
		public T getValue() {
			return value;
		}

		@Override
		public ParseResult<T> mapSource(Function<Source, Source> sourceMapper
		) {
			return new ParseSuccess<>(sourceMapper.apply(source), value);
		}

		@Override
		public String toString() {
			return "Success[" + value + "]";
		}

		@Override
		public ParseException getError() {
			return new ParseException("Can't get Error on a success", source.position);
		}

		@Override
		public ParseResult<T> onErrorAdd(String errorMessage) {
			return this;
		}

		@Override
		public ParseResult<T> onErrorAdd(ParseException exception) {
			return this;
		}

		@Override
		public ParseResult<T> log(LogEntry logEntry) {
			return new ParseResult.ParseSuccess<>(source,value,this.logEntry.append(logEntry));
		}
	}

	public static class ParseFailure<T> extends ParseResult<T>{

		private final Source source;
		private final ParseException errorMessage;
		public ParseFailure(Source source, ParseException errorMessage,LogEntry log){
			super(log);
			this.source = source;
			this.errorMessage = errorMessage;
		}

		public ParseFailure(Source source, ParseException errorMessage) {
			this(source, errorMessage,LogEntryEmpty.inst);
		}

		@Override
		public <U> U match(Function<ParseSuccess<T>, U> success,
						   Function<ParseFailure<T>, U> failure
		) {
			return failure.apply(this);
		}

		@Override
		public boolean isSuccess() {
			return false;
		}

		public Source getSource() {
			return source;
		}

		@Override
		public <R> ParseResult<R> map(Function<T, R> mapper) {
			return new ParseFailure<>(source, errorMessage);
		}

		@Override
		public T getValue() {
			throw new RuntimeException(source.position + ": Can't get value from a failure", errorMessage);
		}

		@Override
		public String toString() {
			return "Failure[" + errorMessage.getMessage() + "]";
		}

		@Override
		public ParseException getError() {
			return errorMessage;
		}

		@Override
		public ParseResult<T> mapSource(Function<Source, Source> sourceMapper
		) {
			return new ParseFailure<>(sourceMapper.apply(source), errorMessage);
		}

		@Override
		public ParseResult<T> onErrorAdd(String errorMessage) {
			return onErrorAdd(new ParseException(errorMessage, source.position));
		}

		@Override
		public ParseResult<T> onErrorAdd(ParseException exception) {
			return new ParseFailure<>(source, this.errorMessage.withCause(exception));
		}

		@Override
		public ParseResult<T> log(LogEntry logEntry) {
			return new ParseResult.ParseFailure<>(source,errorMessage,this.logEntry.append(logEntry));
		}
	}
}
