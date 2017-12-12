package com.persistentbit.sql.updater.parser;

import com.persistentbit.result.OK;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public interface Statement{
	Result<OK> run(UpdateContext context);
}
