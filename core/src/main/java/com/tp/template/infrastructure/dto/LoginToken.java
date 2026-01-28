package com.tp.template.infrastructure.dto;

import java.util.Locale;
import lombok.Builder;

@Builder
public record LoginToken(
        Long userId,
        String username,
        Locale locale
) {

}
