package com.dev.configurations.security;

import com.dev.dto.CustomUser;
import com.dev.exceptions.ResourceNotFoundExceptionHandler;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.dev.configurations.security.SecurityConstants.SYSTEM_ACCOUNT;


@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(getLoggedInUser().orElse(SYSTEM_ACCOUNT));
    }

    public UUID getCurrentUserId() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> ((CustomUser) authentication.getPrincipal()).getUserId())
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler("User Id not found in SecurityContext",
                        "User Id not found in SecurityContext"));
    }

    private Optional<String> getLoggedInUser() {

        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> ((User) authentication.getPrincipal()).getUsername());
    }
}
