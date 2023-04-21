package com.group5.ecommerce.service.auth;

import com.group5.ecommerce.dto.auth.LoginDto;
import com.group5.ecommerce.response.auth.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginDto loginData);
}
