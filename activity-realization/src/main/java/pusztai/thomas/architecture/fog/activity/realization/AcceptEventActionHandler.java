package pusztai.thomas.architecture.fog.activity.realization;

/**
 * Handler for an AcceptEventAction node.
 */
public interface AcceptEventActionHandler {

	/**
	 * Waits for the event and then receives the desired event.
	 */
	void waitForAndAcceptEvent();

}
