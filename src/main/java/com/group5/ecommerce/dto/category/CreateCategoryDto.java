package com.group5.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryDto {
    @NotBlank(message = "El nombre de la categoria es requerida")
    private String name;
}
