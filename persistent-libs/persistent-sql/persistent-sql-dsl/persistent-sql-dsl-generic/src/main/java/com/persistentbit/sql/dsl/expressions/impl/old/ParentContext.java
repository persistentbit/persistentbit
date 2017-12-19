package com.persistentbit.sql.dsl.expressions.impl.old;

import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 30/11/17
 */
public class ParentContext{
	private final String parentAlias;
	private final String itemAlias;

	public ParentContext(String parentAlias, String itemAlias) {
		this.parentAlias = Objects.requireNonNull(parentAlias);
		this.itemAlias = Objects.requireNonNull(itemAlias);
	}

	public String getParentAlias() {
		return parentAlias;
	}

	public String getItemAlias() {
		return itemAlias;
	}

	public String getFullName() {
		return parentAlias + "." + itemAlias;
	}
}
