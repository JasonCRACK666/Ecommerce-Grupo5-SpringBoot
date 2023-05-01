package com.group5.ecommerce.dto.review;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDto {

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
