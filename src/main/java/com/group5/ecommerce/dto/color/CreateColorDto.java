package com.group5.ecommerce.dto.color;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorDto {
    @NotEmpty(message = "El nombre del color es requerido")
    private String name;

    @NotEmpty(message = "El código del color es requerido")
    @Pattern(regexp = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$")
    private String hex;
}
