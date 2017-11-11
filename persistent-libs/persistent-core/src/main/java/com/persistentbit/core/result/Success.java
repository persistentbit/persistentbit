package com.persistentbit.core.result;

import com.persistentbit.core.logging.Log;
import com.persistentbit.core.logging.entries.LogEntry;
import com.persistentbit.core.logging.entries.LogEntryEmpty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * A SUCCESS RESULT
 *
 * @param <T> Result Type
 */
public class Success<T> extends Result<T>{

	private final LogEntry log;
	private final T        value;

	public Success(T value, LogEntry log) {
		this.value = Objects.requireNonNull(value, "Success value is null, use an Empty value instead");
		this.log = Objects.requireNonNull(log, "Log must be non null");
	}


	Success(T value) {
		this(value, LogEntryEmpty.inst);
	}

	@Override
	public Result<T> mapLog(Function<LogEntry, LogEntry> mapper) {
		return new Success<>(value, mapper.apply(log));
	}

	@Override
	public Result<T> doWithLogs(Consumer<LogEntry> effect) {
		return Log.function().code(l -> {
			effect.accept(log);
			return Success.this;
		});

	}

	@Override
	public Optional<Throwable> getEmptyOrFailureException() {
		return Optional.empty();
	}

	@Override
	public <U> Result<U> map(Function<T, U> mapper) {
		if(mapper == null) {
			return Result.failure("map function is null");
		}
		U resultValue;
		try {
			resultValue = mapper.apply(value);
		} catch(Exception e) {
			return failure(e);
		}
		if(resultValue == null) {
			return empty("map returned a null value");
		}
		if(resultValue == this) {
			return (Result<U>) this;
		}
		return new Success<>(resultValue, log);
	}

	@Override
	public <U> Result<U> flatMap(Function<T, Result<U>> mapper) {
		if(mapper == null) {
			return failure("flatMap function is null");
		}
		Result<U> resultValue;
		try {
			resultValue = mapper.apply(value);
		} catch(Exception e) {
			return failure(e);
		}
		if(resultValue == null) {
			return failure("flatMap returned a null result");
		}
		if(resultValue.getLog().isEmpty() && log.isEmpty()) {
			return resultValue;
		}
		return resultValue.mapLog(log::append);
	}

	@Override
	public LogEntry getLog() {
		return log;
	}


	@Override
	public Optional<T> getOpt() {
		return Optional.ofNullable(value);
	}

	@Override
	public T orElseThrow() {
		return value;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Result<T> mapError(Function<Throwable, ? extends Throwable> mapper) {
		return this;
	}

	@Override
	public String toString() {

		try {
			return "Success(" + value + ")";
		} catch(Exception e) {
			return "Success(<Message to string failed for class " + value.getClass() + ">)";
		}
	}

	@Override
	public <E extends Throwable> Result<T> verify(Predicate<T> verification,
												  Function<T, E> failureExceptionSupplier
	) {
		return verification.test(value)
			? this
			: Result.failure(failureExceptionSupplier.apply(value));
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o instanceof Result == false) return false;

		Result<?> result = (Result<?>) o;
		result = result.completed();
		if(result.isPresent() == false) {
			return false;
		}

		return value.equals(result.orElseThrow());
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}


	@Override
	public Result<T> ifEmpty(Consumer<Empty<T>> effect) {
		return this;
	}

	@Override
	public Result<T> ifFailure(Consumer<Failure<T>> effect) {
		return this;
	}

	@Override
	public Result<T> ifPresent(Consumer<Success<T>> effect) {
		return Result.function().code(l -> {
			if(effect == null) {
				return Result.failure("ifPresent with null as effect");
			}
			try {
				effect.accept(this);
				return this;
			} catch(Exception e) {
				return Result.failure(e);
			}
		});
	}

	public T getValue() {
		return value;
	}

	@Override
	public Result<T> filter(Predicate<T> filter) {
		return filter.test(value)
			? this
			: empty();
	}

	@Override
	public Result<String> forEachOrErrorMsg(Consumer<? super T> effect) {
		effect.accept(value);
		return empty();
	}

	@Override
	public Result<Throwable> forEachOrException(Consumer<? super T> effect) {
		effect.accept(value);
		return empty();
	}

	@Override
	public Result<T> cleanLogsOnPresent() {
		return mapLog(l -> LogEntryEmpty.inst);
	}

	@Override
	public Result<T> flatMapFailure(Function<? super Failure<T>, Result<T>> mapper) {
		return this;
	}

	@Override
	public Result<T> flatMapEmpty(Function<? super Empty<T>, Result<T>> mapper
	) {
		return this;
	}

	@Override
	public Result<T> flatMapNoSuccess(BiFunction<Result<T>, Throwable, Result<T>> mapper) {
		return this;
	}

	@Override
	public <U> U match(Function<Success<T>, U> onSuccess, Function<Empty<T>, U> onEmpty,
					   Function<Failure<T>, U> onFailure
	) {
		return Log.function(this).code(l -> onSuccess.apply(this));
	}
}