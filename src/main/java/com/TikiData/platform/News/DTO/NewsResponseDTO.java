package com.TikiData.platform.News.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsResponseDTO {
    private Long id;
    private String tirulo;
    private LocalDate fechaPublicacion;
    private String mensaje;
}
