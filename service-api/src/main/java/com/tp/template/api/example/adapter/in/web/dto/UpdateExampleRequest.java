package com.tp.template.api.example.adapter.in.web.dto;


import com.tp.template.infrastructure.enums.ErrorType;
import com.tp.template.infrastructure.validation.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateExampleRequest(
        @Schema(description = "메시지", example = "테스트 메시지") @NotNull(errorType = ErrorType.E_필수_값_없음) String message
) {

}
