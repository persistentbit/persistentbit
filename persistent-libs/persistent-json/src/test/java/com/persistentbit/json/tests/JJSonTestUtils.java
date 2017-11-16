package com.persistentbit.json.tests;


import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.logging.printing.LogFormatter;
import com.persistentbit.logging.printing.LogPrint;
import com.persistentbit.logging.printing.LogPrintStream;

/**
 * Logging and other utils for testing
 *
 * @author petermuys
 * @since 12/01/17
 */
public class JJSonTestUtils{

	public static LogFormatter testLogFormatter = ModuleLogging.createLogFormatter(true);
	public static LogPrint     testLogPrint     = LogPrintStream.sysOut(testLogFormatter);


}
