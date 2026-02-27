package com.tp.template.api.example.adapter.out.persistence.repository;

import com.tp.template.api.example.application.dto.SearchExampleQuery;
import com.tp.template.entity.example.ExampleEntity;
import org.springframework.data.domain.Page;

public interface ExampleQueryRepository {

    Page<ExampleEntity> search(SearchExampleQuery query);
}
