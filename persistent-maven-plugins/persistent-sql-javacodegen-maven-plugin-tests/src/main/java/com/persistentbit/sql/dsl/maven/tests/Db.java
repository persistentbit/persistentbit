package com.persistentbit.sql.dsl.maven.tests;

import com.persistentbit.sql.dsl.exprcontext.DbContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public class Db{
	public final TCompany company;

	public Db(DbContext dbContext) {

		this.company = new TCompany(dbContext.forTable("persistenttest", "Company"));
	}
}
