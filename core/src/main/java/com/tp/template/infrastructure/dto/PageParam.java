package com.tp.template.infrastructure.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

/**
 * 애플리케이션 레이어 내부에서 사용하는 페이징 값 객체.
 * Web DTO(BaseSearchRequest) → PageParam → Spring Pageable 순으로 변환된다.
 */
public record PageParam(int page, int size, String sort) {

    private static final int MAX_SIZE = 100;

    public static PageParam of(int page, int size, String sort) {
        int safePage = Math.max(0, page);
        int safeSize = Math.min(MAX_SIZE, Math.max(1, size));
        return new PageParam(safePage, safeSize, sort);
    }

    /**
     * Spring Data의 Pageable 로 변환한다.
     * sort 형식: "field,asc" 또는 "field,desc" 또는 "field" (기본값 asc)
     */
    public Pageable toPageable() {
        if (!StringUtils.hasText(sort)) {
            return PageRequest.of(page, size);
        }

        String[] parts = sort.split(",");
        String property = parts[0].trim();
        Sort.Direction direction = (parts.length > 1 && parts[1].trim().equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(page, size, Sort.by(direction, property));
    }
}
