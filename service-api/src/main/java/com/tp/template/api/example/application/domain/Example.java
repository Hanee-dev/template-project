package com.tp.template.api.example.application.domain;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record Example(
        Long id,
        String message,
        LocalDateTime createAt,
        String createdBy,
        LocalDateTime updateAt,
        String updatedBy
) {

    public Example updateMessage(String message) {
        return Example.builder()
                .id(id)
                .message(message)
                .build();
    }
}
