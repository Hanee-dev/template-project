package com.tp.template.api.example.adapter.in.web.dto;


import com.tp.template.infrastructure.enums.ErrorType;
import com.tp.template.infrastructure.validation.NotNull;

public record UpdateExampleRequest(
        @NotNull(errorType = ErrorType.E_필수_값_없음) String message
) {

}
