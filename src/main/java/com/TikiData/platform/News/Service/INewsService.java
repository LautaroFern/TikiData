package com.TikiData.platform.News.Service;

import com.TikiData.platform.Common.Exception.NewsNotFoundException;
import com.TikiData.platform.News.DTO.NewsRequestDTO;
import com.TikiData.platform.News.DTO.NewsResponseDTO;

import java.util.List;

public interface INewsService {
    NewsResponseDTO findById(Long id) throws NewsNotFoundException;
    List<NewsResponseDTO> findAll();
    void deleteNew (Long id) throws NewsNotFoundException;
    NewsResponseDTO updateNews (NewsRequestDTO newsRequestDTO, Long id) throws NewsNotFoundException;
    NewsResponseDTO createNews (NewsRequestDTO newsRequestDTO);
    NewsResponseDTO findByTitle (String title) throws NewsNotFoundException;
}
