package com.persistentbit.core.result;


import com.persistentbit.core.logging.Log;
import com.persistentbit.core.logging.LoggedException;
import com.persistentbit.core.logging.entries.LogEntry;
import com.persistentbit.doc.annotations.DUsesClass;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * An EMPTY result.
 *
 * @param <T> The non-empty result type.
 */
@DUsesClass(value = Result.EmptyException.class,label = "throws")
public class Empty<T> extends Result<T>{

    private Throwable exception;
    private LogEntry log;

    public Empty(Throwable e, LogEntry log) {
        this.exception = Objects.requireNonNull(e);
        this.log = Objects.requireNonNull(log);

    }

    @Override
    public Result<T> mapLog(Function<LogEntry, LogEntry> mapper) {
        return new Empty<>(exception,mapper.apply(log));
    }

    @Override
    public Result<T> doWithLogs(Consumer<LogEntry> effect) {
        return Log.function().code(l -> {
            effect.accept(log);
            return Empty.this;
        });

    }

    @Override
    public Optional<Throwable> getEmptyOrFailureException() {
        return Optional.of(exception);
    }

    public Throwable getException() {
        return exception;
    }

    @Override
    public Result<T> cleanLogsOnPresent() {
        return this;
    }

    @Override
    public LogEntry getLog() {
        return log;
    }

    @Override
    public <U> Empty<U> map(Function<T, U> mapper) {
        return new Empty<>(exception, log);
    }

    @Override
    public <U> Empty<U> flatMap(Function<T, Result<U>> mapper) {
        return new Empty<>(exception, log);
    }

    @Override
    public Optional<T> getOpt() {
        return Optional.empty();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public T orElseThrow() {
        throw new EmptyException(new LoggedException(exception, log));
    }

    @Override
    public String toString() {
        return "Empty(" + exception.getMessage() + ")";
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj instanceof Result == false) {
            return false;
        }
        Result res = (Result) obj;
        return res.completed().isEmpty();
    }

    @Override
	public Result<T> ifEmpty(Consumer<Empty<T>> effect) {
		return Result.function().code(l -> {
			if(effect == null) {
				return Result.failure("ifEmpty with null as effect");
			}
			try {
				effect.accept(this);
				return this;
			} catch(Exception e) {
				return Result.failure(e);
			}
		});

	}

    @Override
	public Result<T> ifFailure(Consumer<Failure<T>> effect) {
		return this;
	}

	@Override
	public Result<T> ifPresent(Consumer<Success<T>> effect) {
		return this;
	}

    @Override
    public Result<T> filter(Predicate<T> filter) {
        return this;
    }

    @Override
    public Result<T> mapError(Function<Throwable, ? extends Throwable> mapper) {
        return this;
    }

    @Override
    public <E extends Throwable> Result<T> verify(Predicate<T> verification,
												  Function<T, E> failureExceptionSupplier
    ) {
        return this;
    }

    @Override
    public Result<String> forEachOrErrorMsg(Consumer<? super T> effect) {
        return empty();
    }

    @Override
    public Result<Throwable> forEachOrException(Consumer<? super T> effect) {
        return empty();
    }

    @Override
    public Result<T> flatMapFailure(Function<? super Failure<T>, Result<T>> mapper
    ) {
        return this;
    }

    @Override
    public Result<T> flatMapEmpty(Function<? super Empty<T>, Result<T>> mapper
    ) {
        if(mapper == null) {
            return failure("flatMapEmpty function is null");
        }
        Result<T> resultValue;
        try {
            resultValue = mapper.apply(this);
        } catch(Exception e) {
            return failure(e);
        }
        if(resultValue == null) {
            return failure("flatMapEmpty returned a null result");
        }
        return resultValue.mapLog(log::append);
    }

    @Override
    public Result<T> flatMapNoSuccess(BiFunction<Result<T>, Throwable, Result<T>> mapper) {
        if(mapper == null) {
            return failure("flatMapNoSuccess function is null");
        }
        Result<T> resultValue;
        try {
            resultValue = mapper.apply(this, getException());
        } catch(Exception e) {
            return failure(e);
        }
        if(resultValue == null) {
            return failure("flatMapNoSuccess returned a null result");
        }
        return resultValue.mapLog(log::append);
    }

    @Override
    public <U> U match(Function<Success<T>, U> onSuccess, Function<Empty<T>, U> onEmpty,
					   Function<Failure<T>, U> onFailure
    ) {
        return Log.function(this).code(l -> onEmpty.apply(this));
    }
}
