package pusztai.thomas.architecture.fog.activity.realization.execution;

/**
 * Service used to resolve and create instances of handler classes needed by an ActivityNodeExecutors.
 */
public interface HandlerResolver {

	/**
	 * Resolves and loads the class of the specified handler.
	 * @param fullyQualifiedName the fully qualified name of the handler class, as read from the activity's JSON file
	 * @return the Class of the specified handler.
	 */
	<T> Class<T> resolveHandlerClass(String fullyQualifiedName);

	/**
	 * Creates an instance of the specified handlerClass using dependency injection.
	 * @param handlerClass the handler class
	 * @return an instance of the specified handler class
	 */
	<T> T getHandlerInstance(Class<T> handlerClass);

	/**
	 * Creates an instance of the specified handlerClass using dependency injection.
	 * @param fullyQualifiedName the fully qualified name of the handler class, as read from the activity's JSON file
	 * @return an instance of the specified handler class
	 */
	default <T> T getHandlerInstance(String fullyQualifiedName) {
		Class<T> handlerClass = this.resolveHandlerClass(fullyQualifiedName);
		return this.getHandlerInstance(handlerClass);
	}

	/**
	 * Resolves and instantiates the specified REST client and finds the specified operation.
	 * @param fullyQualifiedClientName the fully qualified name of the REST client interface
	 * @param operationName the name of the method that represents the operation that should be called
	 * @return an object that contains the REST client's instance and a handle to the target method.
	 */
	RestCallTarget getRestCallTarget(String fullyQualifiedClientName, String operationName);

}
