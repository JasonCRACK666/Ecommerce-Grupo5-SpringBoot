package com.group5.ecommerce.security;

import com.group5.ecommerce.entity.enums.Role;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                HttpMethod.GET,
                                ""
                        )
                            .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/brands",
                                "/api/products",
                                "/api/colors",
                                "/api/categories"
                        )
                            .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/colors/{colorId}",
                                "/api/categories/{categoryId}"
                        )
                        .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/products/{productId}",
                                "/api/categories/{categoryId}",
                                "/api/colors/{colorId}"
                        )
                        .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/products/{productId}",
                                "/api/brands/{brandId}"
                        )
                            .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.GET,
                                ""
                        )
                            .authenticated()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/brands/follow",
                                "/api/reviews/product/{productId}"
                        )
                            .authenticated()
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/reviews/{reviewId}"
                        )
                            .authenticated()
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/reviews/{reviewId}"
                        )
                            .authenticated()
                        .requestMatchers(
                                HttpMethod.PATCH,
                                ""
                        )
                        .authenticated()
                        .anyRequest()
                            .permitAll()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        return http.build();
    }

}
