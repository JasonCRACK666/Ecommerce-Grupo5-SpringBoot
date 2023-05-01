package com.group5.ecommerce.dto.brand;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowBrandDto {
    @NotNull(message = "La marca es requerida")
    @Positive(message = "La marca es invalida")
    private Long brandId;
}
