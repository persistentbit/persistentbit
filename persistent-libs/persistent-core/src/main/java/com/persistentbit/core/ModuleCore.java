package com.persistentbit.core;

import com.persistentbit.core.logging.LoggedException;
import com.persistentbit.core.logging.cleaning.LogCleaner;
import com.persistentbit.core.logging.entries.*;
import com.persistentbit.core.logging.printing.*;
import com.persistentbit.core.utils.UOS;

import java.util.Optional;

/**
 * Global settings for this persistent-core module.
 *
 * @author Peter Muys
 * @since 11/01/2017
 */
public final class ModuleCore {

    public static LogFormatter createLogFormatter(boolean hasColor) {
        LogEntryDefaultFormatting format = hasColor
                ? LogEntryDefaultFormatting.colors
                : LogEntryDefaultFormatting.noColors;

        return LogFormatter.create()
                //LogEntries
                .logIf(LogEntryGroup.class, DefaultLogPrinters.forLogEntryGroup(format))
                .logIf(LogEntryEmpty.class, DefaultLogPrinters.forLogEntryEmpty(format))
                .logIf(LogEntryException.class, DefaultLogPrinters.forLogEntryException(format))
                .logIf(LogEntryFunction.class, DefaultLogPrinters.forLogEntryFunction(format))
                .logIf(LogEntryMessage.class, DefaultLogPrinters.forLogEntryMessage(format))
                //Exceptions
                .logIf(LoggedException.class, LoggedException.createExceptionPrinter(format))
                .logIf(Throwable.class, new DefaultExceptionPrinter(format))
                ;
    }

	/**
	 * Create a log formatter with or without color, depending on the os.<br>
	 *
	 * @return a LogFormatter
	 *
	 * @see #createLogFormatter(boolean)
	 */
	public static LogFormatter createLogFormatter() {
		return createLogFormatter(UOS.hasAnsiColor());
	}


	public static LogPrint consoleLogPrint = LogPrintStream.sysOut(createLogFormatter());


    public static LogCleaner cleaner() {
        return LogCleaner.create()
						 .orIf(LogEntryEmpty.class,(rc, le)-> Optional.empty())
						 .orIf(LogEntryGroup.class,(LogCleaner rc, LogEntryGroup le) ->
                    le.getEntries()
                            .map(e -> rc.clean(e).orElse(null))
                            .filterNulls().isEmpty()
                                ? Optional.<LogEntry>empty()
                                : Optional.<LogEntry>of(le)
                )
						 .orIf(LogEntryException.class,(rc, le) -> Optional.of(le))
						 .orIf(LogEntryMessage.class, (rc, le) -> {
                    switch(le.getLevel()){
                        case error:
                        case warning:
                        case important:
                            return Optional.of(le);
                        default:
                            return Optional.empty();
                    }
                })
						 .orIf(LogEntryFunction.class,(rc, le) ->
                    rc.clean(le.getLogs()).map(s -> le)
                )
        ;
    }
}
