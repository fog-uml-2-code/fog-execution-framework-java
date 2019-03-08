package pusztai.thomas.architecture.fog.validation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Invariants {
	Invariant[] value();
}
