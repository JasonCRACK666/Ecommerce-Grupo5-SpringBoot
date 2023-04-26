package com.group5.ecommerce.dto.product;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    @NotBlank(message = "El titulo es requerido")
    @Size(min = 6, max = 30, message = "Mínimo 6 caracteres y máximo 30 caracteres para el titulo")
    private String title;

    @NotBlank(message = "El titulo es requerido")
    @Size(min = 15, message = "Mínimo 6 caracteres caracteres para la descripción")
    private String description;

    @NotNull(message = "El precio es requerido")
    @Digits(fraction = 2, integer = 5)
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal originalPrice;

    @Max(value = 50)
    @Positive(message = "El porcentaje de descuento debe ser mayor a 0")
    private Integer discountRate;

    @NotNull(message = "Los puntos son requeridos")
    @Positive(message = "Los puntos deben ser mayor a 0")
    private Integer pointValue;

    @NotNull(message = "El stock es requerido")
    @Positive(message = "La stock debe ser mayor a 0")
    private Integer quantity;

    @NotNull(message = "La categoría es requerida")
    @Positive(message = "La categoría es invalida")
    private Long category;

    @NotNull(message = "La marca es requerida")
    @Positive(message = "La marca es invalida")
    private Long brand;

    @NotEmpty(message = "Se require mínimo una imagen")
    private MultipartFile[] images;

    @NotEmpty(message = "Se require mínimo un color")
    private Long[] colors;
}
