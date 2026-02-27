package com.tp.template.infrastructure.dto;

import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Page;

public record BaseSearchResponse<T>(
        List<T> contents,
        PageMeta page
) {

    public static <S, T> BaseSearchResponse<T> from(Page<S> page, Function<S, T> mapper) {
        // @formatter:off
        return new BaseSearchResponse<>(page.getContent().stream().map(mapper).toList(), PageMeta.from(page));
        // @formatter:on
    }

    public record PageMeta(
            int number,
            int size,
            long totalElements,
            int totalPages,
            boolean first,
            boolean last
    ) {

        public static PageMeta from(Page<?> page) {
            return new PageMeta(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(), page.isFirst(), page.isLast());
        }
    }
}
