package com.example.Todo.SecurityConfig;

import com.example.Todo.Service.UserDetailServic;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final Jwtfilter jwtfilter;
    private final UserDetailServic userDetailServic;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable());
        http.sessionManagement(session ->
                session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )
        );
        http.authorizeHttpRequests(auth -> auth

                .requestMatchers("/api/v1/auth/**")
                .permitAll()

                .requestMatchers("/api/v1/tasks/**")
                .authenticated()

                .anyRequest()
                .authenticated()

        )
                .authenticationProvider(authenticationProvider());
        http.addFilterBefore(
                jwtfilter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailServic);

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
