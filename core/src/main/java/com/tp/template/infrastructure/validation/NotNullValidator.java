package com.tp.template.infrastructure.validation;

import com.tp.template.infrastructure.enums.ErrorType;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class NotNullValidator implements ConstraintValidator<NotNull, Object> {

    private ErrorType errorType;

    @Override
    public void initialize(NotNull annotation) {
        errorType = annotation.errorType();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // @formatter:off
        boolean isValid = switch(value) {
            case null -> false;
            case String str -> !StringUtils.isEmpty(str);
            case Collection<?> collection -> !collection.isEmpty();
            case Map<?, ?> map -> !map.isEmpty();
            case CharSequence charSeq -> !charSeq.toString().trim().isEmpty();
            default -> !value.getClass().isArray() || Array.getLength(value) > 0;
        };

        if(!isValid) {
            setErrorMessage(context);
        }

        return isValid;
        // @formatter:on
    }

    private void setErrorMessage(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorType.name())
                .addConstraintViolation();
    }
}