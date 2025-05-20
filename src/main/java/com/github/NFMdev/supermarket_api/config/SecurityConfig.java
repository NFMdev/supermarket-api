package com.github.NFMdev.supermarket_api.config;

import com.github.NFMdev.supermarket_api.repository.UserRepository;
import com.github.NFMdev.supermarket_api.security.JwtAuthorizationFilter;
import com.github.NFMdev.supermarket_api.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain securityFilterChains(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> {
                    try {
                        csrf.disable()
                                .authorizeHttpRequests((auth) -> auth
                                        .requestMatchers("/auth/**").permitAll()
                                        .requestMatchers(
                                                HttpMethod.GET,
                                                "/supermarket/**", "products/**", "sections/**"
                                        )
                                        .authenticated()
                                        .requestMatchers("/supermarket/**", "products/**", "sections/**")
                                        .hasRole("ADMIN")
                                        .anyRequest().authenticated()
                                )
                                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
