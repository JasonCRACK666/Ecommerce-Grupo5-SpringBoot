package com.group5.ecommerce.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String avatar;
    private String userName;
    private String firstName;
    private String lastName;
    private Integer DNI;
    private String email;
}
