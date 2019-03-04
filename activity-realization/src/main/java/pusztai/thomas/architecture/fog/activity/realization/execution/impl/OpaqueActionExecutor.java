package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.OpaqueActionHandler;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.execution.*;

public class OpaqueActionExecutor extends SingleNextNodeExecutorBase {

	public static class Factory implements ActivityNodeExecutorFactory {
		@Override
		public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
			return new OpaqueActionExecutor(nodeInfo, executionManager);
		}
	}

	private final OpaqueActionHandler handler;

	public OpaqueActionExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
		HandlerResolver resolver = executionManager.getBeanContext().getBean(HandlerResolver.class);
		this.handler = resolver.getHandlerInstance(nodeInfo.handler);
	}

	@Override
	public void execute(ActivityNodeExecutor prevNode, Object param) throws ActivityExecutionException {
		this.handler.executeAction();
		this.executeNextNode(null);
	}

	@Override
	public int getMaxRetryCount() {
		return 0;
	}

}
