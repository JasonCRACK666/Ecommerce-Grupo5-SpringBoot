package com.group5.ecommerce.service.auth;

import com.group5.ecommerce.dto.auth.LoginDto;
import com.group5.ecommerce.dto.auth.RegisterUserDto;
import com.group5.ecommerce.response.auth.LoginResponse;
import com.group5.ecommerce.response.auth.RegisterUserResponse;

public interface AuthService {
    LoginResponse login(LoginDto loginData);
    RegisterUserResponse registerUser(RegisterUserDto userData);
}
