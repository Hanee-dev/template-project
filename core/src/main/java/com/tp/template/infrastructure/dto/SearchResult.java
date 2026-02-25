package com.tp.template.infrastructure.dto;

import java.util.List;

public record SearchResult<T>(
        List<T> list
) {

}
