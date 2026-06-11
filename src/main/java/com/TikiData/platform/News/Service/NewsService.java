package com.TikiData.platform.News.Service;

import com.TikiData.platform.Common.Exception.NewsNotFoundException;
import com.TikiData.platform.News.DTO.NewsRequestDTO;
import com.TikiData.platform.News.DTO.NewsResponseDTO;
import com.TikiData.platform.News.Mapper.NewsMapper;
import com.TikiData.platform.News.Model.NewsModel;
import com.TikiData.platform.News.Repository.INewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsService implements INewsService {
    private final INewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    public NewsResponseDTO findById(Long id) throws NewsNotFoundException {
        NewsModel newsModel = newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("Noticia no encontrada"));
        return newsMapper.toDTO(newsModel);
    }

    @Override
    public List<NewsResponseDTO> findAall() {
        return newsRepository.findAll()
                .stream()
                .map(newsMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteNew(Long id) throws NewsNotFoundException {
        NewsModel newsModel = newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("Noticia no encontrada"));
        newsRepository.delete(newsModel);
    }

    @Override
    public NewsResponseDTO updateNews(NewsRequestDTO newsRequestDTO, Long id) throws NewsNotFoundException {
        NewsModel newsModel = newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("Noticia no encontrada"));
        newsModel.setMensaje(newsRequestDTO.getMensaje());
        newsModel.setTitulo(newsRequestDTO.getTitulo());
        newsModel.setFechaPublicacion(LocalDate.now());
        return newsMapper.toDTO(newsRepository.save(newsModel));
    }

    @Override
    public NewsResponseDTO createNews(NewsRequestDTO newsRequestDTO) {
        NewsModel newsModel = newsMapper.toEntity(newsRequestDTO);
        return newsMapper.toDTO(newsRepository.save(newsModel));
    }

    @Override
    public NewsResponseDTO findByTitle(String title) throws NewsNotFoundException{
        NewsModel newsModel = newsRepository.findByTitle(title);
        if (newsModel == null){
            throw new NewsNotFoundException("Noticia no encontrada");
        }
        return newsMapper.toDTO(newsModel);
    }
}
