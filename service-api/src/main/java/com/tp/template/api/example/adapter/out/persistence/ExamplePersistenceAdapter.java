package com.tp.template.api.example.adapter.out.persistence;

import com.tp.template.api.example.adapter.out.persistence.mapper.ExamplePersistenceMapper;
import com.tp.template.api.example.adapter.out.persistence.repository.ExampleRepository;
import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.dto.SearchExampleQuery;
import com.tp.template.api.example.application.port.out.ExamplePort;
import com.tp.template.entity.example.ExampleEntity;
import com.tp.template.infrastructure.enums.ErrorType;
import com.tp.template.infrastructure.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamplePersistenceAdapter implements ExamplePort {

    private final ExampleRepository exampleRepository;

    @Override
    public Example get(long id) {
        return ExamplePersistenceMapper.fromEntity(getEntity(id));
    }

    @Override
    public Example save(Example example) {
        ExampleEntity entity;
        if(example.id() != null) {
            entity = getEntity(example.id());
        }else {
            entity = ExamplePersistenceMapper.toEntity(example);
        }

        return ExamplePersistenceMapper.fromEntity(exampleRepository.save(entity));
    }

    @Override
    public void delete(long id) {
        ExampleEntity entity = getEntity(id);
        exampleRepository.delete(entity);
    }

    @Override
    public Page<Example> search(SearchExampleQuery query) {
        return exampleRepository.search(query)
                .map(ExamplePersistenceMapper::fromEntity);
    }

    private ExampleEntity getEntity(long id) {
        return exampleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorType.E_대상정보_없음));
    }
}
