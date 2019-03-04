package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionManager;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;

public abstract class SingleNextNodeExecutorBase extends ActivityNodeExecutorBase {

	protected final String nextNodeId;

	public SingleNextNodeExecutorBase(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
		this.nextNodeId = nodeInfo.nextNode;
	}

	@Override
	public String getNextNodeIdIfError() {
		return this.nextNodeId;
	}

	protected void executeNextNode(Object param) {
		this.getExecutionManager().executeNode(this, nextNodeId, param);
	}

}
