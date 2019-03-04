package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionException;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionManager;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutor;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;

/**
 * Superclass for nodes that simply call the nextNode and pass on any parameter.
 */
public abstract class PassThroughNodeExecutorBase extends SingleNextNodeExecutorBase {

	public PassThroughNodeExecutorBase(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
	}

	@Override
	public void execute(ActivityNodeExecutor prevNode, Object param) throws ActivityExecutionException {
		this.executeNextNode(param);
	}

	@Override
	public int getMaxRetryCount() {
		return 0;
	}

}
