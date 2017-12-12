package com.persistentbit.sql.updater.parser;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;

import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public interface UpdateContext{

	Optional<String>	getProperty(String name);
	void setProperty(String name, @Nullable String value);

	boolean isChangeNeeded(String name, String author);

	Result<Integer> getSqlResultCount(String sql);

	Result<OK> executeSql(String sql);
}
