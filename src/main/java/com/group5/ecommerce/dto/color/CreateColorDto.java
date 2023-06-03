package com.group5.ecommerce.dto.color;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorDto {
    @NotBlank(message = "El nombre del color es requerido")
    private String name;

    @NotBlank(message = "El código del color es requerido")
    @Pattern(regexp = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$", message = "El código del color es invalido, es un hexadecimal")
    private String hex;
}
