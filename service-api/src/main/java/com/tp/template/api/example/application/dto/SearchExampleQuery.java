package com.tp.template.api.example.application.dto;

import com.tp.template.infrastructure.dto.BaseSearchQuery;
import com.tp.template.infrastructure.dto.PageParam;
import lombok.Getter;

@Getter
public class SearchExampleQuery extends BaseSearchQuery {

    private final String message;

    public SearchExampleQuery(String message, PageParam pageParam) {
        super(pageParam);
        this.message = message;
    }
}
