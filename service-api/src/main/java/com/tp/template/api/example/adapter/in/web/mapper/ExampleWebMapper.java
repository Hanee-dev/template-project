package com.tp.template.api.example.adapter.in.web.mapper;

import com.tp.template.api.example.adapter.in.web.dto.CreateExampleRequest;
import com.tp.template.api.example.application.domain.Example;

public class ExampleWebMapper {

    public static Example toDomain(CreateExampleRequest request) {
        return Example.builder()
                .message(request.message())
                .build();
    }
}
