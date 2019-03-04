package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.AcceptEventActionHandler;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.execution.*;

public class AcceptEventActionExecutor extends SingleNextNodeExecutorBase {

	public static int MAX_RETRY_COUNT = 2;

	public static class Factory implements ActivityNodeExecutorFactory {
		@Override
		public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
			return new AcceptTimeEventActionExecutor(nodeInfo, executionManager);
		}
	}


	private final AcceptEventActionHandler handler;

	public AcceptEventActionExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
		HandlerResolver resolver = executionManager.getBeanContext().getBean(HandlerResolver.class);
		this.handler = resolver.getHandlerInstance(nodeInfo.handler);
	}

	@Override
	public void execute(ActivityNodeExecutor prevNode, Object param) throws ActivityExecutionException {
		try {
			this.handler.waitForAndAcceptEvent();
		} catch (Exception ex) {
			throw new ActivityExecutionException(ex);
		}
		this.executeNextNode(null);
	}

	@Override
	public int getMaxRetryCount() {
		return MAX_RETRY_COUNT;
	}
}
