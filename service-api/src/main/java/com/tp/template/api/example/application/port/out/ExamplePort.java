package com.tp.template.api.example.application.port.out;

import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.dto.SearchExampleQuery;
import org.springframework.data.domain.Page;

public interface ExamplePort {

    Example get(long id);

    Example save(Example example);

    void delete(long id);

    Page<Example> search(SearchExampleQuery query);
}
