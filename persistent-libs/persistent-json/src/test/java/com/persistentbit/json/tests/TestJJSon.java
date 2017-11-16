package com.persistentbit.json.tests;

import com.persistentbit.core.collections.PByteList;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.collections.PMap;
import com.persistentbit.core.collections.PSet;
import com.persistentbit.result.Result;
import com.persistentbit.json.mapping.JJMapper;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.tests.examples.JJTest;
import com.persistentbit.logging.entries.LogContext;
import com.persistentbit.logging.entries.LogEntryMessage;
import com.persistentbit.logging.entries.LogMessageLevel;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;
import com.persistentbit.tuples.Tuple2;

import java.util.Arrays;
import java.util.Date;

/**
 * User: petermuys
 * Date: 25/08/16
 * Time: 18:07
 */

public class TestJJSon {

	static final TestCase testMapperReadWrite = TestCase.name("Test JJMapper Read/Write").code(tr -> {
		JJTest t1 = new JJTest(
			Arrays.asList(new JJSubTest(1, "Peter"), new JJSubTest(2, "Muys")),
			true,
			(short) 1234,
			56578910,
			99999999999L,
			1.23f,
			4.5678,
			new Date(),
			PList.forString().plusAll("Hello", "World", "From", "Peter"),
			PMap.<Integer, String>empty().put(1, "Peter").put(2, "Muys"),
			PSet.<Double>empty().plusAll(1.2, 2.3, 3.4),
			PMap.<String, String>empty().put("prop1", "value1").put("prop2", "value2")
										.map(), JJTest.EnumTest.enum2, new Tuple2<>(1234, 567.8f),
			PList.<Byte>empty().plus((byte) 1).plus((byte) 10).plus((byte) 5).plus((byte) 100),
			PByteList.from(new byte[]{3, 4, 5, 10, 100}),
			Result.failure("This is a failure"),
			Result.success(1234).mapLog(l -> l.append(LogEntryMessage
														  .of(LogMessageLevel.important, new LogContext("file", "class", "method", 1234), "Hallo"))),
			Result.empty("This should be an empty result")
		);
        JJMapper rw   = new JJMapper();
        JJNode   node = rw.write(t1);
		//tr.info(JJPrinter.print(true,node));
		JJTest t2 = rw.read(node,JJTest.class);
        JJNode node2 = rw.write(t2);
		//tr.info(JJPrinter.print(true,node2));
		tr.isEquals(node, node2);
		tr.isEquals(t1, t2);
	});


	public void testAll() {
		TestRunner.runAndPrint(JJSonTestUtils.testLogPrint, TestJJSon.class);
	}

	public static void main(String[] args) {
		new TestJJSon().testAll();
	}
}
