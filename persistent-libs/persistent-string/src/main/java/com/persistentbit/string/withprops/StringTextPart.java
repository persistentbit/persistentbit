package com.persistentbit.string.withprops;

import com.persistentbit.string.UString;

import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class StringTextPart implements TextPart{
	private final String value;

	public StringTextPart(String value) {
		this.value = value;
	}

	@Override
	public String toString(Function<String, Optional<String>> propertyGetter) {
		return value;
	}

	@Override
	public String toString() {
		return "\"" + UString.escapeToJavaString(value) + "\"";
	}
}
