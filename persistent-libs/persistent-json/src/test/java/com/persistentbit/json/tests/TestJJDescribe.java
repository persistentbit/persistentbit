package com.persistentbit.json.tests;


import com.persistentbit.json.mapping.JJMapper;
import com.persistentbit.json.mapping.description.JJTypeDescription;
import com.persistentbit.json.tests.examples.GenericsTestData;
import com.persistentbit.json.tests.examples.JJTest;

/**
 * @author Peter Muys
 * @since 31/08/2016
 */
public class TestJJDescribe {

    public void test1() {
        JJMapper          m  = new JJMapper();
        JJTypeDescription td = m.describe(JJTest.class);
		//System.out.println(JJPrinter.print(true,m.write(td)));
		//System.out.println("All classes:" + td.getAllUsedClassNames());
		//System.out.println(JJPrinter.print(true,m.write(m.describe(GenTestData.class))));
	}


    public void testGenerics() {
        JJMapper          m  = new JJMapper();
		JJTypeDescription td = m.describe(GenericsTestData.class);
		//System.out.println(JJPrinter.print(true,m.write(td)));
	}
}
