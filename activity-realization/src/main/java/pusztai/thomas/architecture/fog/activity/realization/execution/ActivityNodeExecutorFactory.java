package pusztai.thomas.architecture.fog.activity.realization.execution;

import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;

/**
 * Used to create an instance of a specific type of ActivityNodeExecutor.
 */
public interface ActivityNodeExecutorFactory {

	/**
	 * Creates a new ActivityNodeExecutor instance.
	 * @param nodeInfo the DTO containing all information about the ActivityNode, for which the executor should be created
	 * @param executionManager the execution manager, which manages the activity that the node is part of
	 * @return the new ActivityNodeExecutor instance
	 */
	ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager);

}
