package com.group5.ecommerce.service.auth;

import com.group5.ecommerce.dto.auth.LoginDto;
import com.group5.ecommerce.repository.UserRepository;
import com.group5.ecommerce.response.auth.LoginResponse;
import com.group5.ecommerce.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginDto loginData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginData.getEmail(),
                        loginData.getPassword()
                )
        );

        var user = this.userRepository.findByEmail(loginData.getEmail())
                .orElseThrow();

        var jwtToken = this.jwtUtils.generateToken(user);

        return new LoginResponse(jwtToken);
    }

}
