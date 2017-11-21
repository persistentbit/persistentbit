package com.persistentbit.sql.updater.tests;

import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.DbBuilder;
import com.persistentbit.sql.updater.impl.DbBuilderImpl;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/11/17
 */
public class TestUpdater{
	static final TestCase testDbBuilder = TestCase.name("DbBuilder").code(tr -> {
		String driverClassName = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:memory:" + "testDb" + ";create=true";
		//Supplier<Result<Connection>> conSup = () -> Result.failure("No Connection supplier");
		Supplier<Result<Connection>> conSup = DbConnector.fromUrl(driverClassName,url,null,null)
														 .orElseThrow();
		DbBuilder builder = new DbBuilderImpl(null, "sqlTests", "/dbupdates/db_update.sql");
		Supplier<DbTransaction> transSup = () -> DbTransaction.create(conSup);
		tr.isFalse(builder.hasUpdatesThatAreDone().run(transSup.get()).orElseThrow());
		tr.isSuccess(builder.buildOrUpdate().run(transSup.get()));
		tr.isTrue(builder.hasUpdatesThatAreDone().run(transSup.get()).orElseThrow());
		tr.isSuccess(builder.dropAll().run(transSup.get()));

		tr.isFalse(builder.hasUpdatesThatAreDone().run(transSup.get()).orElseThrow());
		tr.isSuccess(builder.buildOrUpdate().run(transSup.get()));
		tr.isTrue(builder.hasUpdatesThatAreDone().run(transSup.get()).orElseThrow());
		tr.isSuccess(builder.dropAll().run(transSup.get()));


	});

	public void testAll() {
		TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TestUpdater.class);
	}

	public static void main(String[] args) {
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		new TestUpdater().testAll();
	}
}
