package com.group5.ecommerce.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReviewDto {
    @NotNull(message = "La puntuación es requerida")
    @Positive(message = "La puntuación debe ser mayor a 0")
    @Max(
            value = 5,
            message = "La puntuación maxima es 5"
    )
    private Integer score;

    @Size(
            min = 5,
            max = 250,
            message = "Mínimo 5 caracteres y máximo 250 caracteres"
    )
    private String comment;
}
