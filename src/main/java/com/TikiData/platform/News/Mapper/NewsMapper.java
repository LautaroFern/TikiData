package com.TikiData.platform.News.Mapper;

import com.TikiData.platform.News.DTO.NewsRequestDTO;
import com.TikiData.platform.News.DTO.NewsResponseDTO;
import com.TikiData.platform.News.Model.NewsModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NewsMapper {
    public NewsModel toEntity(NewsRequestDTO newsRequestDTO){
        NewsModel newsModel = new NewsModel();
        newsModel.setTitle(newsRequestDTO.getTitle());
        newsModel.setMessage(newsRequestDTO.getMessage());
        newsModel.setPublicationDate(LocalDate.now());
        return newsModel;
    }

    public NewsResponseDTO toDTO(NewsModel newsModel){
        NewsResponseDTO newsResponseDTO = new NewsResponseDTO();
        newsResponseDTO.setId(newsModel.getId());
        newsResponseDTO.setTitle((newsModel.getTitle()));
        newsResponseDTO.setPublicationDate(newsModel.getPublicationDate());
        newsResponseDTO.setMessage(newsModel.getMessage());
        return newsResponseDTO;
    }
}
