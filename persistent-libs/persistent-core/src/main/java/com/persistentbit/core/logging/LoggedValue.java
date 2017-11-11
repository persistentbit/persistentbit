package com.persistentbit.core.logging;


import com.persistentbit.core.logging.entries.LogEntry;
import com.persistentbit.doc.annotations.DAggregateOf;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/17
 */
@DAggregateOf(LogEntry.class)
public interface LoggedValue<THIS extends LoggedValue>{
	LogEntry getLog();
	THIS mapLog(Function<LogEntry, LogEntry> f);
}
