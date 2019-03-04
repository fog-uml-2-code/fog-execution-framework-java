package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionManager;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutor;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeType;

public abstract class ActivityNodeExecutorBase implements ActivityNodeExecutor {

	private final String id;
	private final ActivityNodeType type;
	private final String name;
	private final ActivityExecutionManager executionManager;

	public ActivityNodeExecutorBase(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		this.id = nodeInfo.id;
		this.type = nodeInfo.type;
		this.name = nodeInfo.name;
		this.executionManager = executionManager;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public ActivityNodeType getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ActivityExecutionManager getExecutionManager() {
		return executionManager;
	}

	@Override
	public String toString() {
		return type.toString().concat("-".concat(id).concat(": ".concat(name)));
	}

}
