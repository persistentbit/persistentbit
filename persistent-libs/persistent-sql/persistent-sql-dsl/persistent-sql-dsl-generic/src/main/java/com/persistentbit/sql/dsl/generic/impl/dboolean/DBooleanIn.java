package com.persistentbit.sql.dsl.generic.impl.dboolean;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBooleanIn extends DBooleanAbstract{
	private final PList<DExpr> values;

	public DBooleanIn(PList<DExpr> values) {
		this.values = values;
	}
}
