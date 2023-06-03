package com.group5.ecommerce.dto.product;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDto {
    @Size(min = 6, max = 30, message = "Mínimo 6 caracteres y máximo 30 caracteres para el titulo")
    private String title;

    @Size(min = 15, message = "Mínimo 15 caracteres caracteres para la descripción")
    private String description;

    @Digits(fraction = 2, integer = 5)
    @Positive(message = "El precio original debe ser mayor a 0")
    private BigDecimal originalPrice;

    @Max(value = 50, message = "Máximo 50% de descuento")
    @Positive(message = "El porcentaje de descuento debe ser mayor a 0")
    private Integer discountRate;

    @Positive(message = "Los puntos deben ser mayor a 0")
    private Integer pointValue;

    @Positive(message = "La stock debe ser mayor a 0")
    private Integer quantity;

    @Positive(message = "La categoría es invalida")
    private Long category;

    @Positive(message = "La marca es invalida")
    private Long brand;
}
