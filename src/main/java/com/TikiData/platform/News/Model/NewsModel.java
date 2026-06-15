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
    private LocalDate publicationDate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String message;
}
