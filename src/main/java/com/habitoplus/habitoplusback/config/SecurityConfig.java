package com.habitoplus.habitoplusback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
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
                                // Requiere autenticación para cualquier otra solicitud
                                .anyRequest().authenticated()
                )
                // Configuración de inicio de sesión
                .formLogin((form) -> form
                                .loginPage("/login") // Página de inicio de sesión personalizada, si la tienes
                                .permitAll()
                )
                .httpBasic(withDefaults()); // También permite autenticación básica (si la prefieres)

        return http.build();
    }
}
