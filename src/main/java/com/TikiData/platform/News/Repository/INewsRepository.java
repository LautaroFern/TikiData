package com.TikiData.platform.News.Repository;

import com.TikiData.platform.News.Model.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INewsRepository extends JpaRepository<NewsModel, Long> {
    NewsModel findByTitle(String title);
}
