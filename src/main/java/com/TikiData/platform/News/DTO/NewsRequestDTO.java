package com.TikiData.platform.News.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewsRequestDTO {
    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;
}
