package com.persistentbit.sql.updater.tests;

import com.persistentbit.io.IO;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.parser.ParseResult;
import com.persistentbit.parser.source.Source;
import com.persistentbit.sql.updater.parser.ChangeSet;
import com.persistentbit.sql.updater.parser.DbChangeParser;
import com.persistentbit.test.TestCase;

/**
 * TODOC
 *
 * @author petermuys
 * @since 13/12/17
 */
public class TestParser{

	static TestCase parserTest1 = TestCase.name("parserTest1").code( tr -> {
		Source src = Source.asSource(TestParser.class.getResource("/parsetest1.txt"), IO.utf8).orElseThrow();
		ParseResult<ChangeSet> res = DbChangeParser.parseChangeSet().parse(src);

		ChangeSet cs = res.getValue();
	});

	public void testAll() {
		//TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TestParser.class);
	}

	public static void main(String[] args) {
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		new TestParser().testAll();
	}
}
