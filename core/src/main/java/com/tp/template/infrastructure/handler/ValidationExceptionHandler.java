package com.tp.template.infrastructure.handler;

import com.tp.template.infrastructure.dto.ApiResponse;
import com.tp.template.infrastructure.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleValidationException(MethodArgumentNotValidException ex) {
        // @formatter:off
        ErrorType errorType = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .flatMap(error -> ErrorType.from(error.getDefaultMessage()))
                .orElse(ErrorType.E_알수없는_오류);

        return ApiResponse.fail(errorType);
        // @formatter:on
    }
}
