package com.group5.ecommerce.security;

import com.group5.ecommerce.entity.Account;
import com.group5.ecommerce.entity.Cart;
import com.group5.ecommerce.entity.User;
import com.group5.ecommerce.entity.WishList;
import com.group5.ecommerce.entity.enums.Role;
import com.group5.ecommerce.repository.AccountRepository;
import com.group5.ecommerce.repository.CartRepository;
import com.group5.ecommerce.repository.UserRepository;
import com.group5.ecommerce.repository.WishListRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.UUID;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CartRepository cartRepository;
    private final WishListRepository wishListRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var account = new Account();
        this.accountRepository.save(account);

        var cart = new Cart();
        this.cartRepository.save(cart);

        var wishList = new WishList();
        this.wishListRepository.save(wishList);

        var user = User.builder()
                .userName("AdminEcommerce")
                .role(Role.ADMIN)
                .email("adminecommerce@gmail.com")
                .firstName("Admin")
                .lastName("Ecommerce")
                .password(this.passwordEncoder.encode("adminecommerce:1234"))
                .isActive(true)
                .activateCode(UUID.randomUUID())
                .DNI(12345678)
                .account(account)
                .cart(cart)
                .wishList(wishList)
                .build();

        this.userRepository.save(user);

        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                HttpMethod.GET,
                                ""
                        )
                            .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.POST,
                                ""
                        )
                            .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.PUT,
                                ""
                        )
                        .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.DELETE,
                                ""
                        )
                        .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.PATCH,
                                ""
                        )
                            .hasAuthority(Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.GET,
                                ""
                        )
                            .authenticated()
                        .requestMatchers(
                                HttpMethod.POST,
                                ""
                        )
                            .authenticated()
                        .requestMatchers(
                                HttpMethod.DELETE,
                                ""
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
