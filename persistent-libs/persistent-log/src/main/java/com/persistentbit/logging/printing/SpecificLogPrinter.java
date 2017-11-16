package com.persistentbit.logging.printing;


import com.persistentbit.logging.entries.LogEntry;
import com.persistentbit.printable.PrintableText;

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/01/17
 */
@FunctionalInterface
public interface SpecificLogPrinter<E extends LogEntry>{

	PrintableText asPrintable(E logEntry, LogFormatter rootPrinter);


	default <T extends E> SpecificLogPrinter<E> orIf(Class<T> cls, SpecificLogPrinter<T> ep) {
		SpecificLogPrinter self = this;
		return (logEntry, rootPrinter) -> {
			if(cls.isAssignableFrom(logEntry.getClass())) {
				return ep.asPrintable((T) logEntry, rootPrinter);
			}
			return self.asPrintable(logEntry, rootPrinter);
		};
	}


}
