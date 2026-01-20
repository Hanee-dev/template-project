package com.tp.template.entity.example;

import com.tp.template.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Table(name = "example_entity", comment = "예시")
public class ExampleEntity extends BaseEntity {

    @Column(comment = "메시지", columnDefinition = "varchar(100)")
    private String message;
}
