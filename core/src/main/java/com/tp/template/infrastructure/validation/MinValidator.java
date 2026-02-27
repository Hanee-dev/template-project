package com.tp.template.infrastructure.validation;

import com.tp.template.infrastructure.enums.ErrorType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MinValidator {

    public static class ForInteger implements ConstraintValidator<Min, Integer> {

        private ErrorType errorType;
        private int minValue;

        @Override
        public void initialize(Min annotation) {
            errorType = annotation.errorType();
            minValue = (int) Math.ceil(annotation.value());
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            if(value == null) {
                return true;
            }
            boolean isValid = value >= minValue;
            if(!isValid) {
                setErrorMessage(context, errorType);
            }
            return isValid;
        }
    }

    public static class ForLong implements ConstraintValidator<Min, Long> {

        private ErrorType errorType;
        private long minValue;

        @Override
        public void initialize(Min annotation) {
            errorType = annotation.errorType();
            minValue = (long) Math.ceil(annotation.value());
        }

        @Override
        public boolean isValid(Long value, ConstraintValidatorContext context) {
            if(value == null) {
                return true;
            }
            boolean isValid = value >= minValue;
            if(!isValid) {
                setErrorMessage(context, errorType);
            }
            return isValid;
        }
    }

    public static class ForDouble implements ConstraintValidator<Min, Double> {

        private ErrorType errorType;
        private double minValue;

        @Override
        public void initialize(Min annotation) {
            errorType = annotation.errorType();
            minValue = annotation.value();
        }

        @Override
        public boolean isValid(Double value, ConstraintValidatorContext context) {
            if(value == null) {
                return true;
            }
            boolean isValid = value >= minValue;
            if(!isValid) {
                setErrorMessage(context, errorType);
            }
            return isValid;
        }
    }

    public static class ForBigDecimal implements ConstraintValidator<Min, BigDecimal> {

        private ErrorType errorType;
        private BigDecimal minValue;

        @Override
        public void initialize(Min annotation) {
            errorType = annotation.errorType();
            minValue = BigDecimal.valueOf(annotation.value());
        }

        @Override
        public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
            if(value == null) {
                return true;
            }
            boolean isValid = value.compareTo(minValue) >= 0;
            if(!isValid) {
                setErrorMessage(context, errorType);
            }
            return isValid;
        }
    }

    private static void setErrorMessage(ConstraintValidatorContext context, ErrorType errorType) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorType.name())
                .addConstraintViolation();
    }
}
