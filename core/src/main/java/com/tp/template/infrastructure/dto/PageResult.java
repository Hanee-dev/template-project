package com.tp.template.infrastructure.dto;

import java.util.List;
import org.springframework.data.domain.Page;

/**
 * 애플리케이션 레이어의 페이징 결과 값 객체.
 * Spring Data의 Page<T> 를 감싸 Web 레이어와의 결합을 제거한다.
 */
public record PageResult<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {

    public static <T> PageResult<T> from(Page<T> springPage) {
        return new PageResult<>(
                springPage.getContent(),
                springPage.getNumber(),
                springPage.getSize(),
                springPage.getTotalElements(),
                springPage.getTotalPages(),
                springPage.isFirst(),
                springPage.isLast()
        );
    }
}
