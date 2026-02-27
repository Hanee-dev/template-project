package com.tp.template.infrastructure.dto;

import com.tp.template.infrastructure.validation.Max;
import com.tp.template.infrastructure.validation.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;


@Getter
@Setter
public abstract class BaseSearchRequest {

    private static final int MAX_SIZE = 100;

    @Min(0)
    private int page;

    @Min(1)
    @Max(100)
    private int size = 20;

    private String sort;

    public Pageable toPageable() {
        int safePage = page;
        int safeSize = Math.clamp(size, 1, MAX_SIZE);

        if(!StringUtils.hasText(sort)) {
            return PageRequest.of(safePage, safeSize);
        }

        String[] parts = sort.split(",");
        String property = parts[0].trim();
        Sort.Direction direction = (parts.length > 1 && "desc".equalsIgnoreCase(parts[1].trim())) ? Sort.Direction.DESC : Sort.Direction.ASC;

        return PageRequest.of(safePage, safeSize, Sort.by(direction, property));
    }
}
