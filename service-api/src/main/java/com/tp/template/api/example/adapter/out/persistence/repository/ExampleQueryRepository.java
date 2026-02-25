package com.tp.template.api.example.adapter.out.persistence.repository;

import com.tp.template.api.example.application.dto.SearchExampleQuery;
import com.tp.template.entity.example.ExampleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * QueryDSL 커스텀 레포지토리 인터페이스 (Spring Data JPA 프래그먼트 패턴).
 * ExampleRepository 가 이 인터페이스를 상속하면 ExampleQueryRepositoryImpl 이 자동으로 연결된다.
 */
public interface ExampleQueryRepository {

    Page<ExampleEntity> search(SearchExampleQuery query, Pageable pageable);
}
