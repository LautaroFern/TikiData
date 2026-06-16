package com.TikiData.platform.News.Repository;

import com.TikiData.platform.News.Model.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INewsRepository extends JpaRepository<NewsModel, Long> {
    Optional<NewsModel> findByTitle(String title);
}
