package com.persistentbit.string.withprops;

import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public interface TextPart{
	String toString(Function<String, Optional<String>> propertyGetter);

}
