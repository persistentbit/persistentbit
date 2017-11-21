package com.persistentbit.sql.work.tests;

import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.logging.printing.LogPrint;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;
import com.persistentbit.tuples.Tuple2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 20/11/17
 */
public class WorkTest{
	static final TestCase usageTest = TestCase.name("UsageTest").code(tc -> {
		String driverClassName = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:memory:" + "testDb" + ";create=true";
		//Supplier<Result<Connection>> conSup = () -> Result.failure("No Connection supplier");
		Supplier<Result<Connection>> conSup = DbConnector.fromUrl(driverClassName,url,null,null)
			.orElseThrow();
		DbWork<Integer> intWork = DbWork.create(trans -> con -> Result.success(1234));
		DbWork<String> strWork = DbWork.create(trans -> con -> Result.success("Hello"));
		DbWork<String> realWork = DbWork.create(trans -> con -> {
			try(PreparedStatement stat = con.prepareStatement("select  jkm'test' from SYS.SYSSCHEMAS")){
				try(ResultSet res = stat.executeQuery()){
					res.next();
					return Result.success(res.getString(1));
				}
			}
		});

		DbWork<Tuple2<String,Integer>> tuple = DbWork.create(trans -> con -> {
			return intWork.run(trans)
				   .flatMap(intValue ->
					   strWork.run(trans.newTransaction()).map(strValue -> Tuple2.of(strValue,intValue))
				   );
		});

		tc.info(tuple.run(DbTransaction.create(conSup)));
		tc.add(realWork.run(DbTransaction.create(conSup)));
	});

	public void testAll() {
		LogPrint lp = ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		TestRunner.runAndPrint(lp,WorkTest.class);
	}

	public static void main(String[] args) {
		new WorkTest().testAll();
	}
}
