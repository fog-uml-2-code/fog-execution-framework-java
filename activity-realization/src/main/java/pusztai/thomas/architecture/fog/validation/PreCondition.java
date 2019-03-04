package pusztai.thomas.architecture.fog.validation;

import io.micronaut.aop.Around;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.context.annotation.Type;

import java.lang.annotation.*;

/**
 * Defines a precondition, which must be true before the
 * annotated method executes.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Around
@Type(PreConditionInterceptor.class)
public @interface PreCondition {

	/**
	 * The class that implements the actual condition evaluation.
	 */
	Class<? extends MethodInterceptor<?, ?>> value();

}
