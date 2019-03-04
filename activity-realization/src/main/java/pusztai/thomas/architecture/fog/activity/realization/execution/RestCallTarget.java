package pusztai.thomas.architecture.fog.activity.realization.execution;

import java.lang.reflect.Method;

/**
 * Encapsulates the target (REST client and method) of a REST call.
 */
public class RestCallTarget {

	private final Object restClient;
	private final Method operation;

	public RestCallTarget(Object restClient, Method operation) {
		this.restClient = restClient;
		this.operation = operation;
	}

	public Object getRestClient() {
		return restClient;
	}

	public Method getOperation() {
		return operation;
	}
}
