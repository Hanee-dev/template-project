package com.tp.template.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@MappedSuperclass
public class BaseEntity extends BaseCreateEntity {

    @LastModifiedDate
    @Column(comment = "수정일시")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(comment = "수정자")
    private String updatedBy;
}