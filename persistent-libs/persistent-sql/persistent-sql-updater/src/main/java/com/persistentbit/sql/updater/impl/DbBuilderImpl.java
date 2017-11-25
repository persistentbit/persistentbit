package com.persistentbit.sql.updater.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.updater.DbBuilder;
import com.persistentbit.sql.updater.SchemaUpdateHistory;
import com.persistentbit.sql.updater.SqlSnippets;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.utils.Lazy;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.Optional;

/**
 * A {@link DbBuilder} implementation that uses resource files to create/update/drop a database schema.<br>
 * If a snippet exists with the name DropAll, then that snippet will be used in the {@link #dropAll()} method.<br>
 * if a snippet exists with the name OnceBefore, then that snippet will be run once before
 * every call to {@link #buildOrUpdate()} and {@link #dropAll()}.<br>
 *
 * @author Peter Muys
 * @see SchemaUpdateHistory
 * @since 18/06/16
 */
public class DbBuilderImpl implements DbBuilder{

	public static final String onceBeforeSnippetName = "OnceBefore";
	public static final String dropAllSnippetName = "DropAll";

	protected final String packageName;
	protected final SqlSnippets snippets;
	protected final SchemaUpdateHistory updateHistory;


	/**
	 * @param historyPackageName   The packageName, used to keep track of the updates already done.
	 * @param snippets      The Sql snippets
	 * @param updateHistory The {@link SchemaUpdateHistory} to use
	 */
	public DbBuilderImpl(String historyPackageName, SqlSnippets snippets,
						 SchemaUpdateHistory updateHistory
	) {
		this.packageName = historyPackageName;
		this.snippets = snippets;
		this.updateHistory = updateHistory;
	}



	@Override
	public DbWork<OK> buildOrUpdate() {
		return DbWork.function().code(trans -> con -> log ->
			executeSnipIfExists(onceBeforeSnippetName).run(trans)
													  .flatMap(ok -> {
														  PList<String> names = snippets.getAllSnippetNames()
																						.filter(name -> name
																							.equalsIgnoreCase(dropAllSnippetName) == false && name
																							.equalsIgnoreCase(onceBeforeSnippetName) == false);
														  log.info("Found " + names.size() + " snippets");
														  for(String name : names) {
															  log.info("Executing snippet " + name);
															  Result<OK> snipOk =
																  executeSnip(name).run(trans);
															  if(snipOk.isPresent() == false) {
																  return snipOk;
															  }
														  }
														  return OK.result;
													  })
		);
	}

	private final Lazy<PMap<String, Method>> declaredMethods = new Lazy<>(() -> {
		PMap<String, Method> declaredMethods = PMap.empty();

		for(Method m : this.getClass().getDeclaredMethods()) {
			declaredMethods = declaredMethods.put(m.getName().toLowerCase(), m);
		}
		return declaredMethods;
	});

	private DbWork<OK> executeSnipIfExists(String name) {
		return DbWork.function(name).code(trans -> con -> l -> {
			if(snippets.hasSnippet(name)) {
				return executeSnip(name).run(trans);
			}
			return OK.result;
		});
	}

	private DbWork<OK> executeSnip(String name) {
		return DbWork.function(name).code(trans -> con -> l -> {
			//Is Snippet already executed ?
			if(updateHistory.isDone(packageName, name).run(trans).orElseThrow()) {
				return OK.result;
			}
			l.info("DBUpdate for  " + getFullName(name));
			//If a method with this name exists -> execute it
			Optional<Method> optMethod = declaredMethods.get().getOpt(name);
			if(optMethod.isPresent()) {
				optMethod.get().invoke(this, con);
				return OK.result;

			}
			for(DbWork<OK> work : snippets.getAll(name).map(sql -> executeSql(name, sql))) {
				Result<OK> ok = work.run(trans);
				if(ok.isPresent() == false) {
					return ok;
				}
			}
			if(name.equalsIgnoreCase(dropAllSnippetName) || name.equalsIgnoreCase(onceBeforeSnippetName)) {
				return OK.result;
			}
			return updateHistory.setDone(packageName, name).run(trans);
		});
	}

	private String getFullName(String updateName) {
		return packageName + "." + updateName;
	}

	/**
	 * Execute an sql statement.
	 *
	 * @param name The name of the snippet for error reporting
	 * @param sql  The sql statement
	 */
	private DbWork<OK> executeSql(String name, String sql) {
		return DbWork.function(name, sql).code(trans -> con -> l -> {
			try(Statement stat = con.createStatement()) {
				stat.execute(sql);
				return OK.result;
			}
		});

	}


	/**
	 * Executes the snippet 'DropAll' and removes
	 * the update history for this package.<br>
	 *
	 * @return true if dropAll executed without errors
	 */
	@Override
	public DbWork<OK> dropAll() {
		return DbWork.function().code(trans -> con -> l -> {
			if(snippets.hasSnippet(dropAllSnippetName) == false) {
				return Result.failure(new RuntimeException("Can't find SQL code 'DropAll' in " + snippets));
			}
			return executeSnipIfExists(onceBeforeSnippetName).run(trans)
															 .flatMap(ok -> executeSnip(dropAllSnippetName).run(trans))
															 .flatMap(ok -> updateHistory
																 .removeUpdateHistory(packageName).run(trans));

		});
	}

	@Override
	public DbWork<Boolean> hasUpdatesThatAreDone() {
		return DbWork.function().code(trans -> con -> l ->
			updateHistory.getUpdatesDone(packageName).run(trans)
						 .map(p -> p.isEmpty() == false)
		);
	}
}
