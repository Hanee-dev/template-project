package com.tp.template.api.example.adapter.in.web.mapper;

import com.tp.template.api.example.adapter.in.web.dto.CreateExampleRequest;
import com.tp.template.api.example.adapter.in.web.dto.SearchExampleRequest;
import com.tp.template.api.example.adapter.in.web.dto.SearchExampleResponse;
import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.dto.SearchExampleQuery;

public class ExampleWebMapper {

    private ExampleWebMapper() {
        /* This utility class should not be instantiated */
    }

    public static Example requestToDomain(CreateExampleRequest request) {
        return Example.builder()
                .message(request.message())
                .build();
    }

    public static SearchExampleQuery requestToQuery(SearchExampleRequest req) {
        return new SearchExampleQuery(req.getMessage(), req.toPageable());
    }

    public static SearchExampleResponse domainToResponse(Example example) {
        return SearchExampleResponse.builder()
                .id(example.id())
                .message(example.message())
                .build();
    }
}
