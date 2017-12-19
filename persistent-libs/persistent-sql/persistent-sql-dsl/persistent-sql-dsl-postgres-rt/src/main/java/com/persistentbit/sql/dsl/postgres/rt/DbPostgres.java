package com.persistentbit.sql.dsl.postgres.rt;

import com.persistentbit.sql.dsl.generic_old.DbGeneric;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public class DbPostgres extends DbGeneric{

	public DbPostgres(PostgresDbContext context) {
		super(context);
	}

	public DbPostgres(){
		this(new PostgresDbContext());
	}
}
