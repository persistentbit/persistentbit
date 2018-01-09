package com.persistentbit.sqltests;

import com.persistentbit.collections.PList;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.sql.meta.data.DbMetaFunction;
import com.persistentbit.sql.meta.data.DbMetaSchema;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.DbScriptRunner;
import com.persistentbit.sql.updater.SqlSnippets;

import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 6/01/18
 */
public class CreateTestDatabase{

	static public final DbConnector conMySql = DbConnector
		.fromUrl("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:6603", "root", "docker")
		.map(p -> p.pooledConnector(10))
		.orElseThrow();
	static public final DbConnector conH2    = DbConnector
		.fromUrl("org.h2.Driver", "jdbc:h2:tcp://localhost:9092/default", "sa", null)
		.map(p -> p.pooledConnector(10))
		.orElseThrow();
	static public final DbConnector conPg    = DbConnector
		.fromUrl("org.postgresql.Driver", "jdbc:postgresql://localhost:7000/postgres", "postgres", "docker")
		.map(p -> p.pooledConnector(10))
		.orElseThrow();


	static public final DbConnector conOracle = DbConnector
		.fromUrl("oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle")
		.map(p -> p.pooledConnector(10))
		.orElseThrow();

	static public final Supplier<DbTransaction> transMySql = () -> DbTransaction.create(conMySql);
	static public final Supplier<DbTransaction> transH2    = () -> DbTransaction.create(conH2);
	static public final Supplier<DbTransaction> transPg    = () -> DbTransaction.create(conPg);


	static public final PList<Supplier<DbTransaction>> transAll = PList.val(transPg, transMySql, transH2);

	static Result<OK> runScript(DbConnector con, String resourceName, String snipName) {
		return SqlSnippets.load(CreateTestDatabase.class.getResourceAsStream(resourceName))
			.map(snip -> {
				System.out.println("GOT SNIPPETS " + snip.getAllSnippetNames());

				new DbScriptRunner(con, "test", snip
				).run(snipName);
				return OK.inst;
			})
			;
	}

	static Result<OK> runGeneric(DbConnector con) {
		return SqlSnippets.load(CreateTestDatabase.class.getResourceAsStream("/db_creation/generic.sql"))
			.map(snip -> {
				System.out.println("GOT SNIPPETS " + snip.getAllSnippetNames());

				new DbScriptRunner(con, "test", snip
				).run("create");
				return OK.inst;
			})
			;
	}


	static public Result<OK> rebuildMySql() {
		String base = "/db_creation/";
		String sql  = base + "mysql.sql";

		return
			runScript(conMySql, sql, "init")
				.flatMap(ok -> runGeneric(conMySql))
				.flatMap(ok -> runScript(conMySql, sql, "create"));
	}

	static public Result<OK> rebuildPg() {
		String base = "/db_creation/";
		String sql  = base + "postgres.sql";

		return
			runScript(conPg, sql, "init")
				.flatMap(ok -> runGeneric(conPg))
				.flatMap(ok -> runScript(conPg, sql, "create"));
	}

	static public Result<OK> rebuildH2() {
		String base = "/db_creation/";
		String sql  = base + "h2.sql";

		return
			runScript(conH2, sql, "init")
				.flatMap(ok -> runGeneric(conH2))
				.flatMap(ok -> runScript(conH2, sql, "create"));
	}

	static public Result<OK> rebuildOracle() {
		String base = "/db_creation/";
		String sql  = base + "oracle.sql";

		return
			runScript(conOracle, sql, "init")
				//.flatMap(ok -> runGeneric(conOracle))
				.flatMap(ok -> runScript(conOracle, sql, "create"));
	}

	static public Result<OK> rebuildAll() {
		return rebuildMySql()
			.flatMap(ok -> rebuildPg())
			.flatMap(ok -> rebuildH2())
			.flatMap(ok -> rebuildOracle());
	}


	public static void main(String[] args) {
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		rebuildAll().orElseThrow();

		Supplier<DbTransaction> transSup = transPg;
		System.out.println("DATABASE:" + DbMetaDataImporter.getDatabase().run(transSup.get()).orElseThrow());
		PList<DbMetaSchema> allSchemas = DbMetaDataImporter.getAllSchemas().run(transSup.get()).orElseThrow();
		for(DbMetaSchema schema : allSchemas) {
			if(schema.name.equals("pbtest") || schema.name.equals("")) {
				System.out.println("Functions for schema " + schema);
				for(DbMetaFunction function : DbMetaDataImporter.getFunctions(schema).run(transSup.get())
					.orElseThrow()) {
					System.out.println("Function: " + function);
				}
			}
		}


	}
}
