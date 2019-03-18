package pusztai.thomas.architecture.fog.activity.realization.execution;

import io.micronaut.context.BeanContext;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityInfoDto;

/**
 * The central service that is responsible for executing an Activity.
 */
public interface ActivityExecutionManager {

	/**
	 * Defines how many times an ActivityNode can be retried after an error,
	 * before going back to the initial node.
	 */
	static final int MAX_NODE_RETRIES = 2;

	BeanContext getBeanContext();

	/**
	 * @return the ActivityNodeExecutor for the ActivityNode with the specified ID.
	 */
	ActivityNodeExecutor getActivityNode(String id);

	/**
	 * Loads the activity realization specified by the activityInfo.
	 *
	 * This method must be called only once.
	 */
	void loadActivity(ActivityInfoDto activityInfo);

	/**
	 * Starts the loaded activity realization.
	 */
	void startActivity();

	/**
	 * Executes the specified next ActivityNode.
	 * @param currNode the current ActivityNode (i.e., the node that is calling this method)
	 * @param nextNodeId the ID of the next ActivityNode that should be executed
	 * @param param (optional) parameter object that should be passed to the next node's execute() method
	 */
	void executeNode(ActivityNodeExecutor currNode, String nextNodeId, Object param);

}
