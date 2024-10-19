package com.habitoplus.habitoplusback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                                // Permitir acceso sin autenticación a Swagger y recursos relacionados
                                .requestMatchers("/doc/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                                // Permitir acceso sin autenticación a todas las solicitudes
                                .anyRequest().permitAll()
                )
                // No es necesario configurar formLogin ni httpBasic si todo está permitido
                .csrf(csrf -> csrf.disable()); // También puedes deshabilitar CSRF si no necesitas protección CSRF

    return http.build();
    }
}
