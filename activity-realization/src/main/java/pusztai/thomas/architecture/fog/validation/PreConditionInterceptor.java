package pusztai.thomas.architecture.fog.validation;

import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.context.BeanContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

/**
 * Loads the MethodInterceptor defined in an application of @PreCondition
 * and executes the interceptor.
 */
@Singleton
public class PreConditionInterceptor implements MethodInterceptor<Object, Object> {

	@Inject
	private BeanContext beanContext;

	@Override
	public Object intercept(MethodInvocationContext<Object, Object> context) {
		Optional<Class> conditionClass = context.getValue(PreCondition.class, Class.class);
		MethodInterceptor<Object, Object> condition = (MethodInterceptor<Object, Object>) beanContext.getBean(conditionClass.get());
		return condition.intercept(context);
	}

}
