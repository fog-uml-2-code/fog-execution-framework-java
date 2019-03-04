package pusztai.thomas.architecture.fog.activity.realization;

import io.micronaut.http.client.exceptions.HttpClientException;

/**
 * Needs to be implemented by a class that handles a CallOperationAction.
 * @param <P> The type of parameter that is passed to the operation that is called.
 *           If the operation requires more than one parameter, P has to implement
 *           the ParameterSequence interface.
 * @param <R> The type of the return value of the operation.
 */
public interface CallOperationActionHandler<P, R> {

	/**
	 * @return The parameter(s) that should be passed to the upcoming call of the operation.
	 */
	P assembleParameters();

	/**
	 * Handles the return value of the operation.
	 * @param result The return value of the last operation call.
	 */
	void handleResult(R result);

	/**
	 * Handles an error, if one occurred while making the REST request.
	 * @param error The exception describing the error.
	 */
	void handleError(HttpClientException error);

}
