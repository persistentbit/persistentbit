package com.persistentbit.sql.dsl.h2.rt;

import com.persistentbit.sql.dsl.generic_old.DbGeneric;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
public class DbH2 extends DbGeneric{

	public DbH2(H2DbContext context) {
		super(context);
	}

	public DbH2(){
		this(new H2DbContext());
	}
}
