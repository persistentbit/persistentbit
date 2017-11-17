package com.persistentbit.core.collections;

import com.persistentbit.logging.LogCollector;
import com.persistentbit.result.Result;

import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/11/17
 */
public class UPStreams{


	public static <T> Result<PStream<T>> fromSequence(PStream<Result<T>> stream) {
		Optional<Result<T>> optWrong = stream.find(Result::isError);
		LogCollector        lc       = new LogCollector();
		stream.forEach(item -> lc.add(item));
		Result<PStream<T>> result;
		if(optWrong.isPresent()) {
			result = optWrong.get()
				.flatMapFailure(f -> Result.failure(
					new RuntimeException("sequence contains failure", f.getException()))
				).flatMap(t -> Result.failure("Should not happen"));


		} else {
			result = Result.success(stream.lazy()
										  .map(r -> r.orElse(null)));
		}

		return result.withLogs(lc.getEntry());

	}
}
