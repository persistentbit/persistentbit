package com.persistentbit.core.utils;



import java.util.function.Supplier;

/**
 * Utility class for performance testing.<br>
 * Usage Example::<br>
 * TimeMeasurement duration = new TimeMeasurement("[Name of Operation]");<br>
 * ...code...<br>
 * System.out.println(duration.done());<br>
 * in console: [Name of Operation] 1234ms<br>
 * <br>
 * <br>
 * @author Peter Muys
 * @since 19/12/2014
 */
public class TimeMeasurement{

  private final String name;
  private final long   startTime;

  /**
   * Create a new TimeMeasurement with an empty name
   */
  public TimeMeasurement() {
	this("");
  }

  /**
   * Create a new TimeMeasurement with a name.
   *
   * @param name The name of the measurement
   */
  public TimeMeasurement(String name) {
	startTime = System.nanoTime();
	this.name = name;
  }




  /**
   * Ends the measurement and return the result of the measurement.
   *
   * @return The result time.
   */
  public Result done() {
	final long stopTime = System.nanoTime();
	return new Result(){
	  @Override
	  public String toString() {
		return getName() + " " + getDurationInMs() + "ms";
	  }

	  @Override
	  public String getName() {
		return name;
	  }

	  @Override
	  public long getDurationInMs() {
		return getDurationInNanos() / 1000000;
	  }

	  @Override
	  public long getDurationInNanos() {
		return stopTime - startTime;
	  }
	};
  }

  /**
   * Run a time measurement and logs the result to System.out with the name "TimeMeasurement".<br>
   *
   * @param code The code to measure
   */
  public static void runAndLog(Runnable code) {
	runAndLog("TimeMeasurement", code);
  }

  /**
   * Run a time measurement and logs the result to System.out.<br>
   *
   * @param name The name of the measurement
   * @param code The code to measure
   */
  @SuppressWarnings("UseOfSystemOutOrSystemErr")
  public static void runAndLog(String name, Runnable code) {
	TimeMeasurement tm = new TimeMeasurement(name);
	code.run();
	System.out.println(tm.done());

  }

  /**
   * Run a time measurement and logs the result to System.out with the name "TimeMeasurement".<br>
   *
   * @param code The code to measure
   * @param <T>  The code result type
   *
   * @return The result value of the code
   */
  public static <T> T runAndLog(Supplier<T> code) {
	return runAndLog("TimeMeasurement", code);
  }

  /**
   * Run a time measurement and logs the result to System.out.<br>
   *
   * @param name The name of the measurement
   * @param code The code to measure
   * @param <T>  The result value type.
   *
   * @return The result value of the code
   */
  @SuppressWarnings("UseOfSystemOutOrSystemErr")
  public static <T> T runAndLog(String name, Supplier<T> code) {
	TimeMeasurement tm     = new TimeMeasurement(name);
	T               result = code.get();
	System.out.println(tm.done());
	return result;
  }


  /**
   * The result of a measurement.<br>
   */
  public interface Result{

	/**
	 * @return The result in nano seconds
	 */
	long getDurationInNanos();

	/**
	 * @return The result in ms
	 */
	long getDurationInMs();

	/**
	 * @return The name of the measurement
		 */
		String getName();
	}
}
