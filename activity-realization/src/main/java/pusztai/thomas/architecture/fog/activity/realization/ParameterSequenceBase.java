package pusztai.thomas.architecture.fog.activity.realization;

/**
 * Superclass that implements the basic functionality of a ParameterSequence.
 */
public class ParameterSequenceBase implements ParameterSequence {

	private Object[] params;

	protected ParameterSequenceBase(int paramsCount) {
		params = new Object[paramsCount];
	}

	public Object[] getParameters() {
		return params;
	}

	/**
	 * @return The parameter at the specified index.
	 */
	protected Object getParam(int index) {
		return params[index];
	}

	/**
	 * Sets the parameter at the specified index to the specified value.
	 */
	protected void setParam(int index, Object value) {
		params[index] = value;
	}

}
