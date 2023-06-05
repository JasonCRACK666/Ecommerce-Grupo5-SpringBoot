package com.group5.ecommerce.service.user;

import com.group5.ecommerce.response.PaginatedResponse;
import com.group5.ecommerce.response.user.UserResponse;

public interface UserService {
    PaginatedResponse<UserResponse> getAllUsers(int page, int size);
}
