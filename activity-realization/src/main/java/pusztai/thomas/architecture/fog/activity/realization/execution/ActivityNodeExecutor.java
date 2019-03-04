package pusztai.thomas.architecture.fog.activity.realization.execution;

import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeType;

/**
 * Defines the interface for class that is capable of representing and executing a specific ActivityNode.
 */
public interface ActivityNodeExecutor {

	/**
	 * @return the ID of the ActivityNode that is represented by this instance.
	 */
	String getId();

	/**
	 * @return the type of ActivityNode that is represented by this instance.
	 */
	ActivityNodeType getType();

	/**
	 * @return the name of the ActivityNode that is represented by this instance.
	 */
	String getName();

	/**
	 * @return the execution manager, to which this ActivityNodeExecutor belongs.
	 */
	ActivityExecutionManager getExecutionManager();

	/**
	 * Executes this activity node.
	 * @param prevNode the previous node in the activity (the one that caused this node to be executed)
	 * @param param optional parameter for this node
	 * @throws ActivityExecutionException
	 */
	void execute(ActivityNodeExecutor prevNode, Object param) throws ActivityExecutionException;

	/**
	 * @return the number of times that the execution of this node should be retried in case of an error.
	 */
	int getMaxRetryCount();

	/**
	 * Gets the ID of the ActivityNode that should be executed if there
	 * is an error while executing this node.
	 *
	 * If this is null, the initial node will be executed.
	 * @return the ID of the ActivityNode that should be executed if there
	 * is an error while executing this node.
	 */
	String getNextNodeIdIfError();

}
