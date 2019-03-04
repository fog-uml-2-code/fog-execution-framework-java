package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.dto.ConditionInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DecisionNodeCondition {

	private final Object decisionHandler;
	private final Method decisionMethod;
	private final String nextNodeId;

	public DecisionNodeCondition(Object handler, ConditionInfoDto conditionInfo) {
		this.decisionHandler = handler;
		this.nextNodeId = conditionInfo.nextNode;
		Class handlerClass = handler.getClass();
		try {
			this.decisionMethod = handlerClass.getDeclaredMethod(conditionInfo.condition, null);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean evaluateCondition() throws ActivityExecutionException {
		try {
			Object result = this.decisionMethod.invoke(this.decisionHandler, new Object[] {});
			if (result instanceof Boolean) {
				return (Boolean) result;
			} else {
				throw new ActivityExecutionException("DecisionMethod " + this.decisionMethod.getName() + " did not return a boolean value.");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ActivityExecutionException(e);
		}
	}

	public Object getDecisionHandler() {
		return decisionHandler;
	}

	public Method getDecisionMethod() {
		return decisionMethod;
	}

	public String getNextNodeId() {
		return nextNodeId;
	}

}
