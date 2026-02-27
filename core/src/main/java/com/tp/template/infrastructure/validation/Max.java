package com.tp.template.infrastructure.validation;

import com.tp.template.infrastructure.enums.ErrorType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MaxValidator.ForInteger.class, MaxValidator.ForLong.class, MaxValidator.ForDouble.class, MaxValidator.ForBigDecimal.class,})
@Documented
public @interface Max {

    ErrorType errorType() default ErrorType.E_최댓값_검증_실패;

    double value() default 0;

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
