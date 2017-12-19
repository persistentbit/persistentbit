package com.persistentbit.sql.dsl.exprcontext;

import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.generic_old.query.Query;

import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public interface DbTableContext{

	String getTableName();




	Query createQuery(DExprTable table);

	DbTableContext withTableAlias(String tableAlias);

	Optional<String> getTableAlias();


	DbContext	getDbContext();
}
