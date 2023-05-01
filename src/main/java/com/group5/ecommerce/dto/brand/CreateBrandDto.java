package com.group5.ecommerce.dto.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandDto {
    @NotBlank(message = "El nombre de la marca es requerida")
    private String name;

    @NotNull(message = "El logo es requerido")
    private MultipartFile logo;

    @NotNull(message = "El banner es requerido")
    private MultipartFile banner;
}
