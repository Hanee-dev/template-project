package com.tp.template.infrastructure.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseSearchRequest {

    @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다")
    private int page;

    @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다")
    @Max(value = 100, message = "페이지 크기는 100 이하이어야 합니다")
    private int size = 20;

    private String sort;

    public PageParam toPageParam() {
        return PageParam.of(page, size, sort);
    }
}
