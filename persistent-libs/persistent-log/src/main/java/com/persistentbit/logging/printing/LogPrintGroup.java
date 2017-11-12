package com.persistentbit.logging.printing;

import com.persistentbit.core.logging.entries.LogEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A LogPrintGroup contains a list of {@link LogPrint} printers.<br>
 * Printing is done by executing the print of all added {@link LogPrint} instances.
 *
 * @author Peter Muys
 * @since 18/01/2017
 */
public class LogPrintGroup implements LogPrint{

    private List<LogPrint> items;

    private LogPrintGroup(List<LogPrint> items) {
        this.items = Objects.requireNonNull(items);
    }

    public LogPrintGroup() {
        this(Collections.emptyList());
    }

    @Override
    public LogPrint add(LogPrint other) {
    	List<LogPrint> result = new ArrayList<>(items);
    	result.add(other);
        return new LogPrintGroup(result);
    }

    @Override
    public void print(LogEntry logEntry) {
        items.forEach(i -> {
            try {
                i.print(logEntry);
            }catch(Exception e){
                e.printStackTrace();
            }
        });
    }

    @Override
    public void print(Throwable exception) {
        items.forEach(i -> {
            try{
                i.print(exception);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}