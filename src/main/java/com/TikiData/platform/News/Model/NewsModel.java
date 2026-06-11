package com.TikiData.platform.News.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "news")
public class NewsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaPublicacion;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensaje;
}
