package com.persistentbit.sql.updater.parser;

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
	public String toString(UpdateContext context) {
		return context.getProperty(name).orElse(parsedSource);
	}
}
