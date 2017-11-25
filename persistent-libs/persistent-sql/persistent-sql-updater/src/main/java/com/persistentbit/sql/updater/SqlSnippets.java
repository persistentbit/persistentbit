package com.persistentbit.sql.updater;

import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.updater.impl.SqlResourceSnippets;

import java.io.InputStream;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/11/17
 */
public interface SqlSnippets{
	PList<String> getAll(String name);
	boolean hasSnippet(String name);
	PList<String> getAllSnippetNames();

	static Result<SqlSnippets>	load(InputStream in){
		return SqlResourceSnippets.load(in);
	}
}
