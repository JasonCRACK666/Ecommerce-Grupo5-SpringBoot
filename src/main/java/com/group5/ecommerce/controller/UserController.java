package com.group5.ecommerce.controller;

import com.group5.ecommerce.response.PaginatedResponse;
import com.group5.ecommerce.response.user.UserResponse;
import com.group5.ecommerce.service.user.UserService;
import com.group5.ecommerce.utils.ApiConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<PaginatedResponse<UserResponse>> users(
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = ApiConstants.DEFAULT_PAGE_NUMBER
            )
            int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = ApiConstants.DEFAULT_PAGE_SIZE
            )
            int size
    ) {
        return new ResponseEntity<>(this.userService.getAllUsers(page, size), HttpStatus.OK);
    }

}
