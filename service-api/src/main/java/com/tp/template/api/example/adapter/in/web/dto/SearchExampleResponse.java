package com.tp.template.api.example.adapter.in.web.dto;

import lombok.Builder;

@Builder
public record SearchExampleResponse(
        Long id,
        String message
) {

}
