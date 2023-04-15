package com.group5.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PingPongDto {
    @NotBlank(message = "El mensaje es requerido")
    @Size(min = 3, max = 40, message = "Mínimo 3 caracteres y máximo 40 caracteres")
    private String message;
}
