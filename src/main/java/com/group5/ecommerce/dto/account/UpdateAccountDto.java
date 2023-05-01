package com.group5.ecommerce.dto.account;

import com.group5.ecommerce.entity.enums.Theme;

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
            min = 2,
            max = 20,
            message = "Mínimo 2 caracteres y máximo 20 caracteres para el nombre de usuario"
    )
    private String username;

    @Size(
            min = 5,
            max = 30,
            message = "Mínimo 5 caracteres y máximo 30 caracteres para el nombre/s"
    )
    private String firstName;

    @Size(
            min = 5,
            max = 30,
            message = "Mínimo 5 caracteres y máximo 30 caracteres para el apellido/s"
    )
    private String lastName;

    private MultipartFile avatar;

    private MultipartFile banner;

    private Theme theme;

    @Size(
            min = 8,
            message = "Mínimo 2 caracteres y máximo 20 caracteres para el nombre de usuario"
    )
    private String about;
}
