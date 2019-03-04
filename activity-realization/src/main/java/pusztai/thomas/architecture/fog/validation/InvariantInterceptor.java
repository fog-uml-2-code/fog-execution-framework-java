package pusztai.thomas.architecture.fog.validation;

import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.context.BeanContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

/**
 * Loads the MethodInterceptor defined in an application of @Invariant
 * and executes the interceptor.
 */
@Singleton
public class InvariantInterceptor implements MethodInterceptor<Object, Object> {

	@Inject
	private BeanContext beanContext;

	@Override
	public Object intercept(MethodInvocationContext<Object, Object> context) {
		// To avoid infinite loops if the condition class accesses values of the target object.
		if (context.getMethodName().startsWith("get")) {
			return context.proceed();
		}

		Optional<Class> conditionClass = context.getValue(Invariant.class, Class.class);
		MethodInterceptor<Object, Object> condition = (MethodInterceptor<Object, Object>) beanContext.getBean(conditionClass.get());
		return condition.intercept(context);
	}

}
