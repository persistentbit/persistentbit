package com.persistentbit.sqltests;

import com.pbtest.mysql.DbMy;
import com.pbtest.mysql.values.GenData;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;

/**
 * TODOC
 *
 * @author petermuys
 * @since 6/01/18
 */
public class TestSelectMySql extends AbstractTestClass{


	static TestCase baseSelectMySql = TestCase.name("baseSelectMySql").code(tr -> {
		rebuildMySql().orElseThrow();
		GenData rec = DbMy.genData.selectById(100).run(transMySql.get()).orElseThrow();
		tr.isNotEquals(rec, null);
		tr.info(rec);
	});


	public void testAll() {
		TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TestSelectMySql.class);
	}

	public static void main(String[] args) {
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		new TestSelectMySql().testAll();
	}
}
