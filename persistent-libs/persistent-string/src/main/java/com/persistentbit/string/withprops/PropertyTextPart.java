package com.persistentbit.string.withprops;

import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class PropertyTextPart implements TextPart{
	private final String name;
	private final String parsedSource;

	public PropertyTextPart(String name, String parsedSource) {
		this.name = name;
		this.parsedSource = parsedSource;
	}

	@Override
	public String toString(Function<String, Optional<String>> propertyGetter) {
		return propertyGetter.apply(name).orElse(parsedSource);
	}

	@Override
	public String toString() {
		return "${" + name + "}";
	}
}
