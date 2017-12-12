package com.persistentbit.sql.updater.parser;

import com.persistentbit.result.OK;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class ChangeResult{
	private final Change	change;
	private final Result<OK> result;

	public ChangeResult(Change change, Result<OK> result) {
		this.change = change;
		this.result = result;
	}
}
