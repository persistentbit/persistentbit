package com.persistentbit.sql.dsl.statements.work;

import com.persistentbit.sql.work.DbWork;

/**
 * TODOC
 *
 * @author petermuys
 * @since 22/12/17
 */
@FunctionalInterface
public interface DbWorkP2<J1, J2, R>{


	DbWork<R> with(J1 arg1, J2 arg2);
}