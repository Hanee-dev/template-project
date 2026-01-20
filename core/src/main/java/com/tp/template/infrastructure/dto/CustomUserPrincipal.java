package com.tp.template.infrastructure.dto;

import java.util.Collection;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
public class CustomUserPrincipal implements UserDetails {

    private final Long userId;
    private final String username;
    private final Locale locale;
    private final Collection<? extends GrantedAuthority> authorities;

    public static CustomUserPrincipal from(LoginToken loginToken) {
        return new CustomUserPrincipal(loginToken.userId(), loginToken.username(), loginToken.locale(), null);
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }
}