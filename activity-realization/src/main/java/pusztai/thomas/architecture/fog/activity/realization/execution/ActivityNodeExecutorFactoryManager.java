package pusztai.thomas.architecture.fog.activity.realization.execution;

import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;

/**
 * Used to locate the appropriate factory for an ActivityNodeType and
 * create an instance of the appropriate ActivityNodeExecutor using that factory.
 */
public interface ActivityNodeExecutorFactoryManager {

	/**
	 * Creates an instance of the ActivityNodeExecutor type that corresponds to the ActivityNodeType
	 * specified in the nodeInfo
	 * @param nodeInfo the DTO containing all information about the ActivityNode, for which the executor should be created
	 * @param executionManager the execution manager, which manages the activity that the node is part of
	 */
	ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager);

}
