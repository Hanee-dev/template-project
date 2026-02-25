package com.tp.template.api.example.adapter.in.web.dto;

import com.tp.template.api.example.application.domain.Example;
import com.tp.template.infrastructure.dto.PageResult;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record SearchExampleResponse(
        List<ExampleItem> examples,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {

    public static SearchExampleResponse from(PageResult<Example> result) {
        return SearchExampleResponse.builder()
                .examples(result.content().stream().map(ExampleItem::from).toList())
                .page(result.page())
                .size(result.size())
                .totalElements(result.totalElements())
                .totalPages(result.totalPages())
                .first(result.first())
                .last(result.last())
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    public record ExampleItem(Long id, String message) {

        public static ExampleItem from(Example example) {
            return ExampleItem.builder()
                    .id(example.id())
                    .message(example.message())
                    .build();
        }
    }
}
