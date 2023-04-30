package com.group5.ecommerce.service.auth;

import com.group5.ecommerce.dto.auth.LoginDto;
import com.group5.ecommerce.dto.auth.RegisterUserDto;
import com.group5.ecommerce.exception.UserAccountIsActivatedException;
import com.group5.ecommerce.exception.UserAccountNotActivatedException;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.account.AccountResponse;
import com.group5.ecommerce.response.auth.LoginResponse;
import com.group5.ecommerce.response.auth.RegisterUserResponse;

import java.util.UUID;

public interface AuthService {
    AccountResponse getMe(Long userId);
    LoginResponse login(LoginDto loginData) throws UserAccountNotActivatedException;
    RegisterUserResponse registerUser(RegisterUserDto userData);
    MessageResponse activateUser(UUID activateCode) throws UserAccountIsActivatedException;
}
