package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionManager;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutor;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutorFactory;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;

public class MergeNodeExecutor extends PassThroughNodeExecutorBase {

	public static class Factory implements ActivityNodeExecutorFactory {
		@Override
		public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
			return new MergeNodeExecutor(nodeInfo, executionManager);
		}
	}

	public MergeNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
	}

}
