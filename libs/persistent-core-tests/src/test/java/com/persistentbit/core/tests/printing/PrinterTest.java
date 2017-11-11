package com.persistentbit.core.tests.printing;

import com.persistentbit.core.ModuleCore;
import com.persistentbit.core.io.IO;
import com.persistentbit.core.io.IOClassPath;
import com.persistentbit.core.logging.printing.LogPrint;
import com.persistentbit.core.printing.PrintableList;
import com.persistentbit.core.printing.PrintableText;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;

public final class PrinterTest {
    static private final PrintableText text = out -> {
        out.println();
        out.println("Before Indent");
		out.println(PrintableText.indent(iout -> {
			iout.println("First indent");
            iout.println("And The last Line");
			iout.println(PrintableText.indent(iout2 -> {
				iout2.println("The second indent");
                iout2.println("Before BulletList");
                iout2.println(new PrintableList().addItem(bout -> {
                    bout.println("dit is bullet1");
                    bout.println("en de 2e lijn in bullet1");
                }).addItem(bout -> {
                    bout.println("dit is bullet 2");
                    bout.println("en de 2e lijn in bullet 2");
                    bout.println("en de 3e lijn in bullet 2");
                }));
                iout2.println("After BulletList");
            }));
            iout.println("After second indent, in first");
        }));
        out.println("After first indent");

    };


    static final TestCase indentTest = TestCase.name("indent").code(tr -> {
        String txt = text.printToString();
		String expected = IOClassPath.read("/com/persistentbit/core/tests/printing/printertest_result.string", IO.utf8).orElseThrow();
		//tr.isEquals(txt,expected);
    });

    public void testAll() {
		LogPrint lp = ModuleCore.consoleLogPrint.registerAsGlobalHandler();
		TestRunner.runAndPrint(lp,PrinterTest.class);
	}

    public static void main(String... args) throws Exception {
		new PrinterTest().testAll();
    }
}
