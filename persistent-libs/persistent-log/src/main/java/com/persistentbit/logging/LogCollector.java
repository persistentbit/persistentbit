package com.persistentbit.logging;


import com.persistentbit.doc.annotations.DAggregate;
import com.persistentbit.logging.entries.LogEntry;
import com.persistentbit.logging.entries.LogEntryEmpty;


/**
 * A collector for a {@link LogEntry}.<br>
 *
 *
 * @author Peter Muys
 * @since 13/04/2017
 */
public class LogCollector extends AbstractLogEntryLogging{
	@DAggregate
    private LogEntry entry;

    public LogCollector() {
        super(1);
        this.entry = new LogEntryEmpty();
    }

    @Override
    public Void add(LogEntry logEntry) {
        this.entry = this.entry.append(logEntry);
        return null;
    }

    public LogEntry getEntry() {
        return entry;
    }
}
