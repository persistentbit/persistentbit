package com.persistentbit.sql.dsl.generic.expressions.impl.dstring;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DStringFunction extends DStringAbstract{
	private final String name;
	private final PList<DExpr> arguments;

	public DStringFunction(String name,
						   PList<DExpr> arguments
	) {
		this.name = name;
		this.arguments = arguments;
	}
}
