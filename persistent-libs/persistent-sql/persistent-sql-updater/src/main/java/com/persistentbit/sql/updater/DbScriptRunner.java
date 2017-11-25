package com.persistentbit.sql.updater;

import com.persistentbit.collections.PList;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.impl.SqlResourceSnippets;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Loads a Sql script using {@link SqlSnippets} and run selected snippets.
 *
 * @author Peter Muys
 * @see SqlResourceSnippets
 */
public class DbScriptRunner{

	protected final Logger log = Logger.getLogger(getClass().getName());
	protected final Supplier<Result<Connection>> connections;
	protected final String            packageName;
	protected final SqlSnippets sqlSnippets;


	public DbScriptRunner(Supplier<Result<Connection>> connections, String packageName, SqlSnippets snippets) {
		this.connections = connections;
		this.packageName = packageName;
		this.sqlSnippets = snippets;

	}

	public void run(String name) {
		log.info("Executing " + getFullName(name));
		PList<String> snippets = sqlSnippets.getAll(name);
		DbTransaction.create(connections).run(con -> {
			sqlSnippets.getAll(name).forEach(sql -> {
				try {
					try(Statement stat = con.createStatement()) {
						stat.execute(sql);
					}
				} catch(SQLException e) {
					throw new RuntimeException("Error executing " + getFullName(name), e);
				}


			});
			return OK.result;
		}).orElseThrow();
	}

	private String getFullName(String updateName) {
		return packageName + "." + updateName;
	}

}
