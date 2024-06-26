package ru.wdeath.testbotspring.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import ru.wdeath.programagent.lib.web.config.SecurityTemplate;
import ru.wdeath.programagent.lib.web.config.security.ErrorEndpoint;
import ru.wdeath.programagent.lib.web.config.security.TokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityTemplate template;

    public SecurityConfig(TokenFilter tokenFilter, ErrorEndpoint errorEndpoint) {
        this.template = new SecurityTemplate(errorEndpoint, tokenFilter);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return template.filterChain(httpSecurity, null);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authentication -> null;
    }
}
