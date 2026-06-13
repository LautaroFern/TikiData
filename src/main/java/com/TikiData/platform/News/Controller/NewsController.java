package com.TikiData.platform.News.Controller;

import com.TikiData.platform.Common.Exception.NewsNotFoundException;
import com.TikiData.platform.News.DTO.NewsRequestDTO;
import com.TikiData.platform.News.DTO.NewsResponseDTO;
import com.TikiData.platform.News.Service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    @Autowired
    private final NewsService newsService;

    @PostMapping("/create")
    public ResponseEntity<NewsResponseDTO> createNews (@RequestBody @Valid NewsRequestDTO newsRequestDTO){
        NewsResponseDTO newsResponseDTO = newsService.createNews(newsRequestDTO);
        return new ResponseEntity<>(newsResponseDTO,HttpStatus.CREATED);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<NewsResponseDTO>> findAll (){
        List<NewsResponseDTO> list = newsService.findAall();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> updateNews(@RequestBody NewsRequestDTO dto, @PathVariable Long id) {
        try {
            NewsResponseDTO updated = newsService.updateNews(dto, id);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NewsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{title}")
    public ResponseEntity<NewsResponseDTO> findByTitle(@PathVariable String title) {
        try {
            return new ResponseEntity<>(newsService.findByTitle(title), HttpStatus.FOUND);
        }catch (NewsNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> findByID(@PathVariable Long id){
        try {
            return new ResponseEntity<>(newsService.findById(id), HttpStatus.FOUND);
        }catch (NewsNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews (@PathVariable Long id){
        try {
            newsService.deleteNew(id);
            return ResponseEntity.noContent().build();
        }catch (NewsNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
