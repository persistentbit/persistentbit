package com.persistentbit.json.tests;

import com.persistentbit.core.properties.FieldNames;
import com.persistentbit.core.utils.BaseValueClass;
import com.persistentbit.json.mapping.JJMapper;
import com.persistentbit.json.nodes.JJNodeObject;
import com.persistentbit.json.nodes.JJNodeString;
import com.persistentbit.json.security.JJSigning;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;


/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/17
 */
public class TestJJSigning{



	private static class TestClass extends BaseValueClass{
		private final int id;
		private final String userName;

		@FieldNames(names =  {"id","userName"})
		public TestClass(int id, String userName) {
			this.id = id;
			this.userName = userName;
		}
		public TestClass withId(int id){
			return copyWith("id",id);
		}
	}


	static final TestCase testJJSigning = TestCase.name("JJSigning").code(tr -> {
		JJMapper     mapper  = new JJMapper();
		TestClass    data    = new TestClass(1,"Peter Muys");
		JJNodeObject signed  = checkSignedUnsigned(tr,data);
		JJNodeObject signed2 = checkSignedUnsigned(tr,data.withId(2));
		tr.isNotEquals(signed,signed2);

	});

	static private JJNodeObject checkSignedUnsigned(TestRunner tr, Object data){
		JJNodeObject unsigned = new JJMapper().write(data).asObject().orElseThrow();
		JJSigning    signing  = new JJSigning("Dit is een test signing key", "SHA-256");
		JJNodeObject signed   = signing.sign(unsigned).orElseThrow().asObject().orElseThrow();
		JJNodeString sign     = signed.get("signed").get().asString().orElseThrow();
		JJNodeObject signedToUnsigned = signed.get("data")
			.get().asObject().orElseThrow();
		tr.isEquals(unsigned, signedToUnsigned);
		tr.isEquals(signing.unsigned(signed).orElseThrow(), unsigned);

		String unsingedStr = signing.signAsString(unsigned).orElseThrow();
		tr.isEquals(signing.unsignedFromString(unsingedStr).orElseThrow(), unsigned);
		return signed;
	}


	public void testAll() {
		TestRunner.runAndPrint(JJSonTestUtils.testLogPrint, TestJJSigning.class);
	}


	static public void main(String...args){
		new TestJJSigning().testAll();

	}
}
