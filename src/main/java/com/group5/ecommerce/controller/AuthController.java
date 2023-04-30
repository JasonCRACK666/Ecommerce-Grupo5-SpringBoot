package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.auth.ActiveUserDto;
import com.group5.ecommerce.dto.auth.LoginDto;
import com.group5.ecommerce.dto.auth.RegisterUserDto;
import com.group5.ecommerce.exception.UserAccountIsActivatedException;
import com.group5.ecommerce.exception.UserAccountNotActivatedException;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.auth.LoginResponse;
import com.group5.ecommerce.response.auth.RegisterUserResponse;
import com.group5.ecommerce.service.auth.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(path = "login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginDto loginData
    ) throws UserAccountNotActivatedException {
        return new ResponseEntity<>(this.authService.login(loginData), HttpStatus.OK);
    }

    @PostMapping(path = "register")
    public ResponseEntity<RegisterUserResponse> register(
            @RequestBody RegisterUserDto userData
    ) {
        return new ResponseEntity<>(this.authService.registerUser(userData), HttpStatus.OK);
    }

    @PostMapping(path = "activate")
    public ResponseEntity<MessageResponse> activate(
            @Valid @RequestBody ActiveUserDto activeUser
    ) throws UserAccountIsActivatedException {
        return new ResponseEntity<>(
                this.authService.activateUser(activeUser.getActivationCode()),
                HttpStatus.OK
        );
    }

}
