package com.group5.ecommerce.dto.auth;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    @NotBlank(message = "El nombre de usuario es requerido")
    @Size(min = 3, max = 25, message = "Mínimo 3 caracteres y máximo 25 caracteres")
    private String username;

    @NotBlank(message = "Sus nombres son requeridos")
    @Size(min = 3, max = 25, message = "Mínimo 3 caracteres y máximo 25 caracteres")
    private String firstName;

    @NotBlank(message = "Sus apellidos son requeridos")
    @Size(min = 3, max = 25, message = "Mínimo 3 caracteres y máximo 25 caracteres")
    private String lastName;

    @NotBlank(message = "Sus apellidos son requeridos")
    @Email(message = "El correo electrónico es invalido")
    private String email;

    @NotNull(message = "El DNI es requerido")
    @Digits(fraction = 0, integer = 8, message = "El número de DNI son de 8 dígitos")
    @Min(value = 8, message = "El número de DNI son de 8 dígitos")
    private Integer dni;

    @NotBlank(message = "Sus apellidos son requeridos")
    private String password;
}
