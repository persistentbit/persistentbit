package com.persistentbit.logging;


import com.persistentbit.doc.annotations.DUsesClass;
import com.persistentbit.logging.entries.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/01/17
 */
@DUsesClass(LoggedValue.class)
public abstract class AbstractLogEntryLogging {

	protected final int stackEntryIndex;

	public AbstractLogEntryLogging(int stackEntryIndex) {
		this.stackEntryIndex = stackEntryIndex;
	}


	public abstract Void add(LogEntry logEntry);

	public <WL extends LoggedValue> WL add(WL withLogs){
		LogEntry le = withLogs.getLog();
		if(le.isEmpty()==false){
			add(le);
		}
		return withLogs;
	}


	public Void info(Object message){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[stackEntryIndex];
		return add(LogEntryMessage.of(LogMessageLevel.info, new LogContext(ste), objectToString(message)));
	}
	public Void info(String message, Object value){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[stackEntryIndex];
		return add(LogEntryMessage.of(LogMessageLevel.info, new LogContext(ste), objectToString(message) + ": " + objectToString(message)));
	}
	public Void info(String message, Object value, Object...otherValues){
		StackTraceElement ste    = Thread.currentThread().getStackTrace()[stackEntryIndex];
		String            values = objectToString(value) + ", " + objectsToString(otherValues);
		return add(LogEntryMessage.of(LogMessageLevel.info, new LogContext(ste), objectToString(message) + ": " + values));
	}
	public Void important(Object message){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[stackEntryIndex];
		return add(LogEntryMessage.of(LogMessageLevel.important, new LogContext(ste), objectToString(message)));
	}
	public Void important(String message, Object value){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[stackEntryIndex];
		return add(LogEntryMessage.of(LogMessageLevel.important, new LogContext(ste), objectToString(message) + ": " + objectToString(message)));
	}
	public Void important(String message, Object value, Object...otherValues){
		StackTraceElement ste    = Thread.currentThread().getStackTrace()[stackEntryIndex];
		String            values = objectToString(value) + ", " + objectsToString(otherValues);
		return add(LogEntryMessage.of(LogMessageLevel.important, new LogContext(ste), objectToString(message) + ": " + values));
	}
	public Void warning(Object message){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[stackEntryIndex];
		return add(LogEntryMessage.of(LogMessageLevel.warning, new LogContext(ste), objectToString(message)));
	}
	public Void warning(String message, Object value){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[stackEntryIndex];
		return add(LogEntryMessage.of(LogMessageLevel.warning, new LogContext(ste), objectToString(message) + ": " + objectToString(message)));
	}
	public Void error(Object message){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[stackEntryIndex];
		return add(LogEntryMessage.of(LogMessageLevel.error, new LogContext(ste), objectToString(message)));
	}
	public Void error(String message, Object value){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[stackEntryIndex];
		return add(LogEntryMessage.of(LogMessageLevel.error, new LogContext(ste), objectToString(message) + ": " + objectToString(message)));
	}

	public Void exception(Throwable cause) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[stackEntryIndex];
		return add(new LogEntryException(new LogContext(ste), cause));
	}




	public static String objectToString(Object message){
		if(message == null){
			return "null";
		}
		try{
			return message.toString();
		}catch(Exception e){
			return "<Message to string failed for class " + message.getClass() + ">";
		}
	}
	public static  String objectsToString(Object...others){
		String res = "";
		for(Object param : others){
			if(res.isEmpty() == false){
				res += ", ";
			}
			res += objectToString(param);
		}
		return res;
	}


}
