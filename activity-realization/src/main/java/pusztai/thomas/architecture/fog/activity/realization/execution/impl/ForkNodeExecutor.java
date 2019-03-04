package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionException;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionManager;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutor;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutorFactory;

public class ForkNodeExecutor extends ActivityNodeExecutorBase {

	public static class Factory implements ActivityNodeExecutorFactory {
		@Override
		public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
			return new ForkNodeExecutor(nodeInfo, executionManager);
		}
	}

	private final String[] nextNodes;

	public ForkNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
		this.nextNodes = nodeInfo.nextNodes;
	}

	@Override
	public void execute(ActivityNodeExecutor prevNode, Object param) throws ActivityExecutionException {
		for (String nextNode : this.nextNodes) {
			this.getExecutionManager().executeNode(this, nextNode, null);
		}
	}

	@Override
	public int getMaxRetryCount() {
		return 0;
	}

	@Override
	public String getNextNodeIdIfError() {
		return null;
	}
}
