package com.dev.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.ZonedDateTime;
import java.util.Optional;

@Configuration
@EnableJpaRepositories("com.dev.repo")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware", dateTimeProviderRef = "auditorDateTimeProvider")
@EnableTransactionManagement
public class DBAuditConfig {

    @Bean
    public DateTimeProvider auditorDateTimeProvider() {
        return () -> Optional.ofNullable(ZonedDateTime.now());
    }
}
