package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionManager;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutor;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutorFactory;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;

public class InitialNodeExecutor extends PassThroughNodeExecutorBase {

	public static class Factory implements ActivityNodeExecutorFactory {
		@Override
		public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
			return new InitialNodeExecutor(nodeInfo, executionManager);
		}
	}

	public InitialNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
	}

}
