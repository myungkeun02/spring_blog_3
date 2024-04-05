package org.myungkeun.spring_blog_3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {
            "/**",
            "/api/**",
            "/api/auth/**",
            "/api/user/**",
            "/swagger-ui/**"
    };

    private final AuthenticationProvider authenticationProvider;
}
