package com.tp.template.api.example.adapter.in.web.mapper;

import com.tp.template.api.example.adapter.in.web.dto.CreateExampleRequest;
import com.tp.template.api.example.adapter.in.web.dto.SearchExampleRequest;
import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.dto.SearchExampleQuery;

public class ExampleWebMapper {

    public static Example toDomain(CreateExampleRequest request) {
        return Example.builder()
                .message(request.message())
                .build();
    }

    public static SearchExampleQuery map(SearchExampleRequest req) {
        return new SearchExampleQuery(req.getMessage(), req.toPageParam());
    }
}
