package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import io.micronaut.context.BeanContext;
import pusztai.thomas.architecture.fog.activity.realization.execution.HandlerResolver;
import pusztai.thomas.architecture.fog.activity.realization.execution.RestCallTarget;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Method;

@Singleton
public class HandlerResolverImpl implements HandlerResolver {

	private final BeanContext beanContext;
	private final ClassLoader classLoader = HandlerResolverImpl.class.getClassLoader();

	@Inject
	public HandlerResolverImpl(BeanContext beanContext) {
		this.beanContext = beanContext;
	}

	@Override
	public <T> Class<T> resolveHandlerClass(String fullyQualifiedName) {
		try {
			return (Class<T>) classLoader.loadClass(fullyQualifiedName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override public <T> T getHandlerInstance(Class<T> handlerClass) {
		return beanContext.getBean(handlerClass);
	}

	@Override
	public RestCallTarget getRestCallTarget(String fullyQualifiedClientName, String operationName) {
		Class restClientClass = this.resolveHandlerClass(fullyQualifiedClientName);
		Method targetOperation = null;

		Method[] methods = restClientClass.getMethods();
		for (Method m : methods) {
			if (m.getName().equals(operationName)) {
				targetOperation = m;
				break;
			}
		}
		if (targetOperation == null) {
			throw new RuntimeException(new NoSuchMethodException("The method " + operationName + "could not be found in class " + fullyQualifiedClientName));
		}

		Object restClient = this.beanContext.getBean(restClientClass);
		return new RestCallTarget(restClient, targetOperation);
	}

}
