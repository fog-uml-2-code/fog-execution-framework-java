package pusztai.thomas.architecture.fog.validation;

import io.micronaut.aop.Around;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.context.annotation.Type;

import java.lang.annotation.*;

/**
 * Defines an invariant, which must always be true.
 *
 * In practice it is evaluated after the execution of every method
 * that does not start with get*.
 */
@Documented
@Repeatable(Invariants.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Around
@Type(InvariantInterceptor.class)
public @interface Invariant {

	/**
	 * The class that implements the actual condition evaluation.
	 */
	Class<? extends MethodInterceptor<?, ?>> value();

}
