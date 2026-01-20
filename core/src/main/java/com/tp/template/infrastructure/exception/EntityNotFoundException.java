package com.tp.template.infrastructure.exception;

import com.tp.template.infrastructure.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {

    public final ErrorType errorType;
}
