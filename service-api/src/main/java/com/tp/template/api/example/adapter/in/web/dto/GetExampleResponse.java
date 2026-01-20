package com.tp.template.api.example.adapter.in.web.dto;

import com.tp.template.api.example.application.domain.Example;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetExampleResponse(String message) {

    public static GetExampleResponse from(Example example) {
        return GetExampleResponse.builder()
                .message(example.message())
                .build();
    }
}
