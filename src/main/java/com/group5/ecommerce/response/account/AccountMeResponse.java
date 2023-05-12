package com.group5.ecommerce.response.account;

import com.group5.ecommerce.entity.enums.Role;
import com.group5.ecommerce.entity.enums.Theme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountMeResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private Theme theme;
    private Role role;
}
