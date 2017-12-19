package com.persistentbit.sql.dsl.tables;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.statements.select.Query;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public interface Table<EALL extends DExpr<J>,J>{
	Table as(String aliasName);

	EALL all();

	Query query();
}
