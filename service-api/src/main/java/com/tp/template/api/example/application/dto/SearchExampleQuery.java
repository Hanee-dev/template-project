package com.tp.template.api.example.application.dto;

import com.tp.template.infrastructure.dto.BaseSearchQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class SearchExampleQuery extends BaseSearchQuery {

    private final String message;

    public SearchExampleQuery(String message, Pageable pageable) {
        super(pageable);
        this.message = message;
    }
}
