package com.TikiData.platform.News.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsResponseDTO {
    private Long id;
    private String title;
    private LocalDate publicationDate;
    private String message;
}
