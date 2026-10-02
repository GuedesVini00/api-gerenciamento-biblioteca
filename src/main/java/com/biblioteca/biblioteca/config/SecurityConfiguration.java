package com.biblioteca.biblioteca.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.HttpStatusAccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                    }))
                .authorizeHttpRequests(auth ->

                        //AUTORIZAÇÃO PARA REGISTRO E LOGIN:
                    auth.requestMatchers(HttpMethod.POST,"/auth/**").permitAll()

                            // OBRAS:
                            .requestMatchers(HttpMethod.GET, "/obras/**").permitAll()

                            .requestMatchers(HttpMethod.POST, "/obras/**")
                            .hasAnyRole("FUNCIONARIO", "ADMIN")

                            .requestMatchers(HttpMethod.PUT, "/obras/**")
                            .hasAnyRole("FUNCIONARIO", "ADMIN")

                            .requestMatchers(HttpMethod.DELETE, "/obras/**")
                            .hasAnyRole("FUNCIONARIO","ADMIN")


                            // COPIAS:
                            .requestMatchers(HttpMethod.GET, "/copias/**")
                            .hasAnyRole("FUNCIONARIO", "ADMIN")

                            .requestMatchers(HttpMethod.POST, "/copias/**")
                            .hasAnyRole("FUNCIONARIO", "ADMIN")

                            .requestMatchers(HttpMethod.PUT, "/copias/**")
                            .hasAnyRole("FUNCIONARIO", "ADMIN")

                            .requestMatchers(HttpMethod.DELETE, "/copias/**")
                            .hasAnyRole("FUNCIONARIO","ADMIN")

                            
                            // LEITORES:

                            .requestMatchers(HttpMethod.GET, "/leitores/**")
                            .hasAnyRole("FUNCIONARIO", "ADMIN")

                            .requestMatchers(HttpMethod.PUT, "/leitores/**")
                            .hasAnyRole("FUNCIONARIO", "ADMIN")

                            .requestMatchers(HttpMethod.DELETE, "/leitores/**")
                            .hasRole("ADMIN")

                            .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }
   
}
