package com.tp.template.infrastructure.handler;

import com.tp.template.infrastructure.dto.CommonApiResponse;
import com.tp.template.infrastructure.enums.ErrorType;
import com.tp.template.infrastructure.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonApiResponse<Void> handleEntityNotFoundException(EntityNotFoundException ex) {
        return CommonApiResponse.fail(ex.getErrorType());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonApiResponse<Void> handleRuntimeException(RuntimeException ex) {
        return CommonApiResponse.fail(ErrorType.E_알수없는_오류);
    }
}
