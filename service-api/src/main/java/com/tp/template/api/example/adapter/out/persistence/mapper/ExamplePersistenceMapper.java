package com.tp.template.api.example.adapter.out.persistence.mapper;

import com.tp.template.api.example.application.domain.Example;
import com.tp.template.entity.example.ExampleEntity;

public class ExamplePersistenceMapper {

    public static Example fromEntity(ExampleEntity entity) {
        return Example.builder()
                .id(entity.getId())
                .message(entity.getMessage())
                .createAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .updateAt(entity.getUpdatedAt())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public static ExampleEntity toEntity(Example domain) {
        return ExampleEntity.builder()
                .id(domain.id())
                .message(domain.message())
                .build();
    }
}
