package com.tp.template.api.example.application.port.in;

import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.dto.SearchExampleQuery;
import org.springframework.data.domain.Page;

public interface ExampleQuery {

    Example get(long id);

    Page<Example> search(SearchExampleQuery query);
}
