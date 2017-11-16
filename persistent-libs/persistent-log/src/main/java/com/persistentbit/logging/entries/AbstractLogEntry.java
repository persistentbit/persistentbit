package com.persistentbit.logging.entries;

import com.persistentbit.logging.ModuleLogging;

/**
 * Abstract Log Entry class
 *
 * @author Peter Muys
 * @since 10/01/2017
 */
public abstract class AbstractLogEntry implements LogEntry{


    @Override
    public String toString() {
		return ModuleLogging.createLogFormatter(false).printableLog(this).printToString();
	}


}
