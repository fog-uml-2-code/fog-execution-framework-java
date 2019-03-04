package pusztai.thomas.architecture.fog.activity.realization.execution;

/**
 * Describes a recoverable error during the execution of an activity.
 */
public class ActivityExecutionException extends Exception {

	public ActivityExecutionException(String msg) {
		super(msg);
	}

	public ActivityExecutionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ActivityExecutionException(Throwable cause) {
		super(cause);
	}

}
