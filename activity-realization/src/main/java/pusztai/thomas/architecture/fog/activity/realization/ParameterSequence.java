package pusztai.thomas.architecture.fog.activity.realization;

/**
 * Stores a sequence of parameters for an operation that requires more than one argument.
 */
public interface ParameterSequence {

	/**
	 * @return The parameters for the operation in the order defined by the operation.
	 */
	Object[] getParameters();

}
