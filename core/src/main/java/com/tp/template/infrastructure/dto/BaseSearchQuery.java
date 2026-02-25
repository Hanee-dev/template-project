package com.tp.template.infrastructure.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class BaseSearchQuery {

    private final PageParam pageParam;
}
