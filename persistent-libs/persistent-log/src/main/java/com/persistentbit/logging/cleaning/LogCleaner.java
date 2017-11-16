package com.persistentbit.logging.cleaning;


import com.persistentbit.logging.entries.LogEntry;

import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * Class to clean a LogEntry if there are no
 * important messages.
 *
 * @author Peter Muys
 * @since 12/01/2017
 */

public final class LogCleaner{
    private LinkedHashMap<Class,SpecificLogCleaner> cleaners;

    private LogCleaner(LinkedHashMap<Class, SpecificLogCleaner> cleaners) {
        this.cleaners = cleaners;
    }


    public static LogCleaner create() {
        return new LogCleaner(new LinkedHashMap<>());
    }

    @SuppressWarnings("unchecked")
    public Optional<LogEntry> clean(LogEntry logEntry){
        SpecificLogCleaner cleaner = cleaners.get(logEntry.getClass());
        if(cleaner != null){
            return cleaner.clean(this,logEntry);
        }
        return cleaners.entrySet().stream()
            .filter(kv -> kv.getKey().isAssignableFrom(logEntry.getClass()))
            .findFirst()
            .map(t -> t.getValue().clean(this,logEntry))
            .orElseGet(() -> Optional.of(logEntry));

    }

    public <L extends LogEntry> LogCleaner orIf(Class<L> cls, SpecificLogCleaner<L> cleaner){
        LinkedHashMap<Class,SpecificLogCleaner> newMap = new LinkedHashMap<>(cleaners);
        newMap.put(cls,cleaner);
        return new LogCleaner(newMap);
    }
}
