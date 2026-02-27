package com.tp.template.api.example.adapter.out.persistence.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tp.template.api.example.application.dto.SearchExampleQuery;
import com.tp.template.entity.example.ExampleEntity;
import com.tp.template.entity.example.QExampleEntity;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class ExampleQueryRepositoryImpl implements ExampleQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ExampleEntity> search(SearchExampleQuery query) {
        QExampleEntity example = QExampleEntity.exampleEntity;
        Pageable pageable = query.getPageable();
        BooleanExpression condition = messageContains(query.getMessage());

        List<ExampleEntity> content = queryFactory.selectFrom(example)
                .where(condition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(example.id.desc())
                .fetch();

        long total = Objects.requireNonNullElse(
                queryFactory.select(example.count())
                        .from(example)
                        .where(condition)
                        .fetchOne(), 0L);

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression messageContains(String message) {
        return StringUtils.hasText(message) ? QExampleEntity.exampleEntity.message.containsIgnoreCase(message) : null;
    }
}
