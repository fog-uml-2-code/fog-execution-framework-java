package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.execution.*;

public class DecisionNodeExecutor extends ActivityNodeExecutorBase {

	public static class Factory implements ActivityNodeExecutorFactory {
		@Override
		public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
			return new DecisionNodeExecutor(nodeInfo, executionManager);
		}
	}

	private final DecisionNodeCondition[] conditions;
	private final String elseNextNodeId;

	public DecisionNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
		this.elseNextNodeId = nodeInfo.elseNextNode;

		HandlerResolver resolver = executionManager.getBeanContext().getBean(HandlerResolver.class);
		Object decisionHandler = resolver.getHandlerInstance(nodeInfo.handler);
		this.conditions = new DecisionNodeCondition[nodeInfo.conditions.length];
		for (int i = 0; i < nodeInfo.conditions.length; ++i) {
			this.conditions[i] = new DecisionNodeCondition(decisionHandler, nodeInfo.conditions[i]);
		}
	}

	@Override
	public void execute(ActivityNodeExecutor prevNode, Object param) throws ActivityExecutionException {
		for (DecisionNodeCondition condition : this.conditions) {
			if (condition.evaluateCondition()) {
				this.getExecutionManager().executeNode(this, condition.getNextNodeId(), null);
				return;
			}
		}
		this.getExecutionManager().executeNode(this, this.elseNextNodeId, null);
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
