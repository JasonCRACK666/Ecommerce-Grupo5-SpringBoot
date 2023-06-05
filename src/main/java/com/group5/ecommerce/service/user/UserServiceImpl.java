package com.group5.ecommerce.service.user;

import com.group5.ecommerce.entity.User;
import com.group5.ecommerce.repository.UserRepository;
import com.group5.ecommerce.response.PaginatedResponse;
import com.group5.ecommerce.response.user.UserMapper;
import com.group5.ecommerce.response.user.UserResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public PaginatedResponse<UserResponse> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> paginatedUsers = this.userRepository.findAll(pageable);

        List<User> users = paginatedUsers.getContent();

        List<UserResponse> mappedUsers = UserMapper.INSTANCE.toListResponse(users);

        return new PaginatedResponse<>(
                mappedUsers,
                paginatedUsers.getNumber(),
                paginatedUsers.getSize(),
                paginatedUsers.getTotalElements(),
                paginatedUsers.getTotalPages(),
                paginatedUsers.isLast()
        );
    }
}
