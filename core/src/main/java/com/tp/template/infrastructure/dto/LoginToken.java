package com.tp.template.infrastructure.dto;

import java.util.Locale;

public record LoginToken(
        Long userId,
        String username,
        Locale locale
) {

}
