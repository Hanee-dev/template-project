package com.tp.template.api.example.adapter.out.persistence.repository;

import com.tp.template.entity.example.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long>, ExampleQueryRepository {

}
