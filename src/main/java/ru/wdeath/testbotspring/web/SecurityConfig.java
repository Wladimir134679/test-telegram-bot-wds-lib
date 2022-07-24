package ru.wdeath.testbotspring.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.wdeath.managerbot.lib.web.config.SecurityTemplate;
import ru.wdeath.managerbot.lib.web.config.security.ErrorEndpoint;
import ru.wdeath.managerbot.lib.web.config.security.TokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenFilter tokenFilter;
    private final ErrorEndpoint errorEndpoint;
    private final SecurityTemplate template;

    public SecurityConfig(TokenFilter tokenFilter, ErrorEndpoint errorEndpoint) {
        this.tokenFilter = tokenFilter;
        this.errorEndpoint = errorEndpoint;
        this.template = new SecurityTemplate(errorEndpoint, tokenFilter);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return template.filterChain(httpSecurity, null);
    }
}
