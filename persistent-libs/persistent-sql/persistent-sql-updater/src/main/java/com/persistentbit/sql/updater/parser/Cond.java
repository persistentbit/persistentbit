package com.persistentbit.sql.updater.parser;

import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public interface Cond{

	Result<Boolean> run(UpdateContext context);
}
