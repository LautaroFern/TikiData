package com.TikiData.platform.MiniGame.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MiniGameRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @Size(max = 300, message = "La descripción no puede superar los 300 caracteres")
    private String description;

    @NotBlank(message = "La URL externa es obligatoria")
    private String externalUrl;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String imageUrl;
}