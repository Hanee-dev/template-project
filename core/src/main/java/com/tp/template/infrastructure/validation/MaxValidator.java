package com.tp.template.infrastructure.validation;

import com.tp.template.infrastructure.enums.ErrorType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MaxValidator {

    private MaxValidator() {
        /* This utility class should not be instantiated */
    }

    public static class ForInteger implements ConstraintValidator<Max, Integer> {

        private ErrorType errorType;
        private int maxValue;

        @Override
        public void initialize(Max annotation) {
            errorType = annotation.errorType();
            maxValue = (int) Math.floor(annotation.value());
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            if(value == null) {
                return true;
            }
            boolean isValid = value <= maxValue;
            if(!isValid) {
                setErrorMessage(context, errorType);
            }
            return isValid;
        }
    }

    public static class ForLong implements ConstraintValidator<Max, Long> {

        private ErrorType errorType;
        private long maxValue;

        @Override
        public void initialize(Max annotation) {
            errorType = annotation.errorType();
            maxValue = (long) Math.floor(annotation.value());
        }

        @Override
        public boolean isValid(Long value, ConstraintValidatorContext context) {
            if(value == null) {
                return true;
            }
            boolean isValid = value <= maxValue;
            if(!isValid) {
                setErrorMessage(context, errorType);
            }
            return isValid;
        }
    }

    public static class ForDouble implements ConstraintValidator<Max, Double> {

        private ErrorType errorType;
        private double maxValue;

        @Override
        public void initialize(Max annotation) {
            errorType = annotation.errorType();
            maxValue = annotation.value();
        }

        @Override
        public boolean isValid(Double value, ConstraintValidatorContext context) {
            if(value == null) {
                return true;
            }
            boolean isValid = value <= maxValue;
            if(!isValid) {
                setErrorMessage(context, errorType);
            }
            return isValid;
        }
    }

    public static class ForBigDecimal implements ConstraintValidator<Max, BigDecimal> {

        private ErrorType errorType;
        private BigDecimal maxValue;

        @Override
        public void initialize(Max annotation) {
            errorType = annotation.errorType();
            maxValue = BigDecimal.valueOf(annotation.value());
        }

        @Override
        public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
            if(value == null) {
                return true;
            }
            boolean isValid = value.compareTo(maxValue) <= 0;
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
