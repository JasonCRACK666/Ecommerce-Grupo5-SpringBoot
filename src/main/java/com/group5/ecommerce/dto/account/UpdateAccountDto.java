package com.group5.ecommerce.dto.account;

import com.group5.ecommerce.entity.enums.Theme;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountDto {
    @Size(
            min = 3,
            max = 25,
            message = "Mínimo 3 caracteres y máximo 25 caracteres para el nombre de usuario"
    )
    private String username;

    @Size(
            min = 3,
            max = 25,
            message = "Mínimo 3 caracteres y máximo 25 caracteres para el nombre/s"
    )
    private String firstName;

    @Size(
            min = 3,
            max = 25,
            message = "Mínimo 3 caracteres y máximo 25 caracteres para el apellido/s"
    )
    private String lastName;

    @Email(message = "El correó electrónico es invalido")
    private String email;

    private Theme theme;

    private MultipartFile avatar;

    private MultipartFile banner;

    @Size(
            min = 8,
            message = "Mínimo 8 caracteres para la información"
    )
    private String about;
}
