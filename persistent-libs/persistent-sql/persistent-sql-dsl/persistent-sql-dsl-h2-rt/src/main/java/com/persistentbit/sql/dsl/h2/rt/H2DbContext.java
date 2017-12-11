package com.persistentbit.sql.dsl.h2.rt;

import com.persistentbit.sql.dsl.exprcontext.impl.GenericDbContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
public class H2DbContext extends GenericDbContext{

	public H2DbContext() {
		super(new H2SqlContext());
	}
}
