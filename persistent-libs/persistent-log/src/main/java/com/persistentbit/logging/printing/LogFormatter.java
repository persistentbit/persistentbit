package com.persistentbit.logging.printing;


import com.persistentbit.doc.annotations.DAggregateOf;
import com.persistentbit.logging.entries.LogEntry;
import com.persistentbit.printable.PrintableText;

import java.util.LinkedHashMap;

/**
 * Printer for {@link LogEntry} values
 *
 * @author petermuys
 * @since 30/12/16
 */
@DAggregateOf(value = SpecificExceptionPrinter.class,otherLabel = "exceptionPrinters")
@DAggregateOf(value = SpecificLogPrinter.class,otherLabel = "logPrinters")
public final class LogFormatter{
    private final LinkedHashMap<Class, SpecificExceptionPrinter> exceptionPrinters;
    private final LinkedHashMap<Class, SpecificLogPrinter>         logPrinters;

    private LogFormatter(LinkedHashMap<Class, SpecificExceptionPrinter> exceptionPrinters,
                         LinkedHashMap<Class, SpecificLogPrinter> logPrinters
    ) {
        this.exceptionPrinters = exceptionPrinters;
        this.logPrinters = logPrinters;
    }

    private LogFormatter() {
        this(new LinkedHashMap<>(), new LinkedHashMap<>());
    }

    public static LogFormatter create() {
        return new LogFormatter();
    }


    public <T extends LogEntry> LogFormatter logIf(Class<T> logEntry, SpecificLogPrinter<T> specificLogPrinter) {
        LinkedHashMap<Class, SpecificLogPrinter> newMap = new LinkedHashMap<>(logPrinters);
        newMap.put(logEntry,specificLogPrinter);
        return new LogFormatter(exceptionPrinters, newMap);
    }

    public <T extends Throwable> LogFormatter logIf(Class<T> exception,
													SpecificExceptionPrinter<T> specificExceptionPrinter
    ) {
        LinkedHashMap<Class, SpecificExceptionPrinter> newMap = new LinkedHashMap<>(exceptionPrinters);
        newMap.put(exception,specificExceptionPrinter);
        return new LogFormatter(newMap, logPrinters);
    }

    @SuppressWarnings("unchecked")
    public PrintableText printableLog(LogEntry logEntry){
        if(logEntry == null){
            return null;
        }
        SpecificLogPrinter slp = logPrinters.get(logEntry.getClass());
        if(slp != null){
            return slp.asPrintable(logEntry,this);
        }
        return logPrinters.entrySet().stream()
            .filter(keyValue -> keyValue.getKey().isAssignableFrom(logEntry.getClass()))
            .findFirst()
            .map(kv -> kv.getValue().asPrintable(logEntry,this))
            .orElseGet(()-> PrintableText.from(logEntry));

    }
    @SuppressWarnings("unchecked")
    public PrintableText printableException(Throwable exception){
        SpecificExceptionPrinter sep = exceptionPrinters.get(exception.getClass());
        if(sep != null){
            return sep.asPrintable(exception,this);
        }
        return exceptionPrinters.entrySet().stream()
            .filter(keyValue -> keyValue.getKey().isAssignableFrom(exception.getClass()))
            .findFirst()
            .map(kv -> kv.getValue().asPrintable(exception,this))
            .orElseGet(()-> PrintableText.from(exception));

    }

}
