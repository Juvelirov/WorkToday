package com.example.worktodayproject.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    /**
     * Бин шифрации пароля
     * @return зашифрованный пароль
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Конфигурировать защиту
     * @param http конфигуратор защиты
     * @return конфигурация
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/public/**").permitAll()
                        .requestMatchers("/api/v1/private/admin").hasRole("ADMIN")
                        .requestMatchers("/api/v1/private/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/private/hr/**").hasAnyRole("HR", "ADMIN")
                        .requestMatchers("/api/v1/private/student/**").hasAnyRole("STUDENT", "HR", "ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


}
