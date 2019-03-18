package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import io.micronaut.http.client.exceptions.HttpClientException;
import pusztai.thomas.architecture.fog.activity.realization.CallOperationActionHandler;
import pusztai.thomas.architecture.fog.activity.realization.ParameterSequence;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.execution.*;

import java.lang.reflect.InvocationTargetException;

public class CallOperationActionExecutor extends SingleNextNodeExecutorBase {

	public static final int MAX_RETRY_COUNT = 2;

	public static class Factory implements ActivityNodeExecutorFactory {
		@Override
		public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
			return new CallOperationActionExecutor(nodeInfo, executionManager);
		}
	}

	private final RestCallTarget restCallTarget;
	private final CallOperationActionHandler<Object, Object> callOpActionHandler;

	public CallOperationActionExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
		HandlerResolver resolver = executionManager.getBeanContext().getBean(HandlerResolver.class);

		this.restCallTarget = resolver.getRestCallTarget(nodeInfo.target, nodeInfo.operation);
		this.callOpActionHandler = resolver.getHandlerInstance(nodeInfo.handler);
	}

	@Override
	public void execute(ActivityNodeExecutor prevNode, Object param) throws ActivityExecutionException {
		Object[] restParams = this.assembleRestParameters();
		Object result = null;

		try {
			result = this.restCallTarget.getOperation().invoke(this.restCallTarget.getRestClient(), restParams);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			throw new ActivityExecutionException(ex);
		} catch (HttpClientException ex) {
			this.callOpActionHandler.handleError(ex);
			throw new ActivityExecutionException(ex);
		}

		this.callOpActionHandler.handleResult(result);
		this.executeNextNode(null);
	}

	@Override
	public int getMaxRetryCount() {
		return MAX_RETRY_COUNT;
	}

	private Object[] assembleRestParameters() {
		Object restParam = this.callOpActionHandler.assembleParameters();
		if (restParam == null) {
			return new Object[] {};
		}
		if (restParam instanceof ParameterSequence) {
			ParameterSequence paramSequence = (ParameterSequence) restParam;
			return paramSequence.getParameters();
		}
		return new Object[] { restParam };
	}

}
