package com.persistentbit.sql;

import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.logging.printing.LogFormatter;
import com.persistentbit.logging.printing.LogPrint;
import com.persistentbit.logging.printing.LogPrintStream;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/01/17
 */
public class ModuleSql{

	public static LogFormatter createLogFormatter(boolean inColor) {
		return ModuleLogging.createLogFormatter(inColor);
	}

	public static LogPrint logPrint = LogPrintStream.sysOut(createLogFormatter(true));
}
