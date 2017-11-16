package com.persistentbit.logging.printing;


import com.persistentbit.logging.entries.LogEntry;
import com.persistentbit.printable.PrintableText;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * TODO: Add comment
 *
 * @author Peter Muys
 * @since 18/01/2017
 */
public class LogPrintToFile implements LogPrint{

	private final LogFormatter          logFormatter;
	private final Charset charset;
	private final Function<Object,File> fileSupplier;

	public LogPrintToFile(Charset charset, LogFormatter logFormatter, Function<Object, File> fileSupplier) {
		this.charset = charset;
		this.logFormatter = logFormatter;
		this.fileSupplier = fileSupplier;
    }

	public static LogPrintToFile perDay(LogFormatter logFormatter, File rootPath, String appName) {
		return new LogPrintToFile(
			Charset.defaultCharset(),
			logFormatter,
			dayFileSupplier(rootPath,appName + "_",".log.txt")
        );
    }

    public static Function<Object,File> dayFileSupplier(File rootPath, String prefix, String postfix){
		return value -> {
			if(rootPath.exists() == false){
				if(rootPath.mkdirs() == false){
					throw new RuntimeException("Can't create dayFile path: " + rootPath);
				}
			}
			if(rootPath.isDirectory() == false){
				throw new RuntimeException("Not a directory: " + rootPath);
			}
			return new File(rootPath,prefix + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + postfix);
		};

    }


    @Override
    public void print(LogEntry logEntry) {
		doPrint(logEntry, logFormatter.printableLog(logEntry));
	}

    @Override
    public void print(Throwable exception) {
		doPrint(exception, logFormatter.printableException(exception));
	}

    private File currentFile;
    private Writer currentWriter;
    private synchronized void doPrint(Object value, PrintableText printableText){
        try {
            File f = fileSupplier.apply(value);
            if (f.equals(currentFile) == false) {
                if (currentFile != null) {
                    currentWriter.close();
                    currentWriter = null;
                }
                currentFile = null;
				currentWriter = new OutputStreamWriter(new FileOutputStream(f,true),charset);
                currentFile = f;
            }
            if(currentWriter != null) {
                currentWriter.write(printableText.printToString());
                currentWriter.flush();
            }
        }catch(Throwable t){
			System.err.println(logFormatter.printableException(t));
		}
    }
}