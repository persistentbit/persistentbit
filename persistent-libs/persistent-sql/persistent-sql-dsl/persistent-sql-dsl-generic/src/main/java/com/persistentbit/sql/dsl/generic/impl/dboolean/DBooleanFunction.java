package com.persistentbit.sql.dsl.generic.impl.dboolean;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBooleanFunction extends DBooleanAbstract{
	private String name;
	private PList<DExpr> arguments;

	public DBooleanFunction(String name,
							PList<DExpr> arguments
	) {
		this.name = name;
		this.arguments = arguments;
	}
}
