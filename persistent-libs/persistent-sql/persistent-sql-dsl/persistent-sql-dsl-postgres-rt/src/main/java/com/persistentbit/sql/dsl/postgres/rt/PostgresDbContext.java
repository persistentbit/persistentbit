package com.persistentbit.sql.dsl.postgres.rt;

import com.persistentbit.sql.dsl.exprcontext.impl.GenericDbContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class PostgresDbContext extends GenericDbContext{

	public PostgresDbContext() {
		super(new PostgresSqlContext());
	}
}
