package com.persistentbit.logging.printing;

import com.persistentbit.core.printing.PrintTextWriter;
import com.persistentbit.core.printing.PrintableText;
import com.persistentbit.doc.annotations.DCreates;

/**
 * Default LogPrinter
 *
 * @author Peter Muys
 * @since 11/01/2017
 */
@DCreates(value = SpecificLogPrinter.class)
public class DefaultLogPrinters{


    public static SpecificLogPrinter<LogEntryEmpty> forLogEntryEmpty(LogEntryDefaultFormatting format) {
        return (logEntry, rootPrinter) -> PrintableText.empty;
    }

    public static SpecificLogPrinter<LogEntryException> forLogEntryException(LogEntryDefaultFormatting format) {
        return (logEntry, rootPrinter) ->
                (PrintTextWriter out) -> {
                    out.println(
                            format.msgStyleError + logEntry.getCause().getMessage() +
                                    format.timeStyle + "\t… " + logEntry.getContext().map(s -> format.formatTime(s.getTimestamp()) + " ").orElse("") +
                                    format.classStyle + logEntry.getContext().map(s -> s.getClassName() + "(" + s.getFileName() + ":" + s.getSourceLine() + ")").orElse("")
                    );
                    out.print(PrintableText.indent(rootPrinter.printableException(logEntry.getCause())));
                };
    }

    public static SpecificLogPrinter<LogEntryMessage> forLogEntryMessage(LogEntryDefaultFormatting format) {
        return (logEntry, rootPrinter) ->
                (PrintTextWriter out) ->
                        out.println(
                                format.msgStyleDebug + logEntry.getMessage() +
                                        format.timeStyle + "\t… " + logEntry.getContext()
                                        .map(s -> format.formatTime(s.getTimestamp()) + " ")
                                        .orElse("") +
                                        format.classStyle + logEntry.getContext()
                                        .map(s -> s.getClassName() + "(" + s.getFileName() + ":" + s.getSourceLine() + ")")
                                        .orElse("")
                        );
    }

    public static SpecificLogPrinter<LogEntryFunction> forLogEntryFunction(LogEntryDefaultFormatting format) {
        return (logEntry, rootPrinter) ->
                (PrintTextWriter out) -> {

                    String functionName = logEntry.getContext().map(s -> {
                        String fun     = s.getMethodName();
                        String clsName = s.getClassName();
                        int    i       = clsName.lastIndexOf('.');
                        if (i >= 0) {
                            clsName = clsName.substring(i + 1);
                        }
                        clsName = clsName.replace('$', '.');
                        clsName = logEntry.getThisContainer().orElse(clsName);
                        return clsName + "." + fun;
                    }).orElse("unknownFunction");


                    String duration = logEntry.getTimestampDone().map(td ->
                            logEntry.getContext().map(c -> " " + (td - c.getTimestamp()) + "ms ")
                                    .orElse("")
                    ).orElse("");

                    String returnValue = logEntry.getResult().map(r -> ": " + r).orElse("");
                    out.println(
                            format.functionStyle + functionName +
                                    format.functionParamsStyle + logEntry.getParams().map(p -> "(" + p + ")").orElse("(?)") +
                                    format.functionResultStyle + returnValue +
                                    format.timeStyle + "\t… " + logEntry.getContext().map(s -> format.formatTime(s.getTimestamp()) + " ").orElse("") +
                                    format.durationStyle + duration +
                                    format.classStyle + logEntry.getContext().map(s -> s.getClassName() + "(" + s.getFileName() + ":" + s.getSourceLine() + ")").orElse("")
                    );
                    out.print(PrintableText.indent(rootPrinter.printableLog(logEntry.getLogs())));
                };
    }

    public static SpecificLogPrinter<LogEntryGroup> forLogEntryGroup(LogEntryDefaultFormatting format) {
        return (logEntry, rootPrinter) ->
                (PrintTextWriter out) -> logEntry.getEntries().forEach(le ->
                        out.print(rootPrinter.printableLog(le))
                )
                ;
    }
}
