package com.persistentbit.sql.dsl.statements.work;

import com.persistentbit.sql.work.DbWork;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/12/17
 */
@FunctionalInterface
public interface DbWorkP4<J1, J2, J3, J4, R>{


	DbWork<R> with(J1 arg1, J2 arg2, J3 arg3, J4 arg4);
}
