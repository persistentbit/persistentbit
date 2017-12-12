package com.persistentbit.sql.updater.parser;

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
	public String toString(UpdateContext context) {
		return value;
	}
}
