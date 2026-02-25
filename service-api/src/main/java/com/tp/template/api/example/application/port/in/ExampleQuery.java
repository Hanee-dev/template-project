package com.tp.template.api.example.application.port.in;

import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.dto.SearchExampleQuery;
import com.tp.template.infrastructure.dto.PageResult;

public interface ExampleQuery {

    Example get(long id);

    PageResult<Example> search(SearchExampleQuery query);
}
