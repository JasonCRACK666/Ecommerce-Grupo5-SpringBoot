package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.auth.LoginDto;
import com.group5.ecommerce.dto.auth.RegisterUserDto;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.auth.LoginResponse;
import com.group5.ecommerce.response.auth.RegisterUserResponse;
import com.group5.ecommerce.service.auth.AuthServiceImp;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {
    @Autowired
    private AuthServiceImp authService;

    @PostMapping(path = "login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginDto loginData
    ) {
        return new ResponseEntity<>(this.authService.login(loginData), HttpStatus.OK);
    }

    @PostMapping(path = "register")
    public ResponseEntity<RegisterUserResponse> register(
            @Valid @RequestBody RegisterUserDto userData
    ) {
        return new ResponseEntity<>(this.authService.registerUser(userData), HttpStatus.OK);
    }

    @PostMapping(path = "activate/{activateCode}")
    public ResponseEntity<MessageResponse> activate(
            @PathVariable("activateCode") UUID activateCode
    ) {
        return new ResponseEntity<>(this.authService.activateUser(activateCode), HttpStatus.OK);
    }

}
