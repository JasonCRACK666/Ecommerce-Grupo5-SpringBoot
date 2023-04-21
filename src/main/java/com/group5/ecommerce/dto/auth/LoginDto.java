package com.group5.ecommerce.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @Email(message = "El correó es invalido")
    @NotBlank(message = "El correo electrónico es requerido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;
}
