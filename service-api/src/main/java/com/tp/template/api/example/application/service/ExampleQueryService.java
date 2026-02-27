package com.tp.template.api.example.application.service;

import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.dto.SearchExampleQuery;
import com.tp.template.api.example.application.port.in.ExampleQuery;
import com.tp.template.api.example.application.port.out.ExamplePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExampleQueryService implements ExampleQuery {

    private final ExamplePort examplePort;

    @Override
    public Example get(long id) {
        return examplePort.get(id);
    }

    @Override
    public Page<Example> search(SearchExampleQuery query) {
        return examplePort.search(query);
    }
}
