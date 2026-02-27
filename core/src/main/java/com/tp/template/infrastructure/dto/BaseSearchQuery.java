package com.tp.template.infrastructure.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@RequiredArgsConstructor
public abstract class BaseSearchQuery {

    private final Pageable pageable;
}
