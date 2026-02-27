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
@Constraint(validatedBy = {MinValidator.ForInteger.class, MinValidator.ForLong.class, MinValidator.ForDouble.class, MinValidator.ForBigDecimal.class,})
@Documented
public @interface Min {

    ErrorType errorType() default ErrorType.E_최솟값_검증_실패;

    double value() default 0;

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
