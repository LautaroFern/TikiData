package com.TikiData.platform.News.Mapper;

import com.TikiData.platform.News.DTO.NewsRequestDTO;
import com.TikiData.platform.News.DTO.NewsResponseDTO;
import com.TikiData.platform.News.Model.NewsModel;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {
    public NewsModel toEntity(NewsRequestDTO newsRequestDTO){
        NewsModel newsModel = new NewsModel();
        newsModel.setTitulo(newsModel.getTitulo());
        newsModel.setMensaje(newsRequestDTO.getMensaje());
        return newsModel;
    }

    public NewsResponseDTO toDTO(NewsModel newsModel){
        NewsResponseDTO newsResponseDTO = new NewsResponseDTO();
        newsResponseDTO.setId(newsResponseDTO.getId());
        newsResponseDTO.setTirulo(newsResponseDTO.getTirulo());
        newsResponseDTO.setFechaPublicacion(newsModel.getFechaPublicacion());
        newsResponseDTO.setMensaje(newsResponseDTO.getMensaje());
        return newsResponseDTO;
    }
}
