package com.TikiData.platform.Forum.Repository;

import com.TikiData.platform.Forum.Model.ForumModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IForumRepository extends JpaRepository<ForumModel, Long> {
    ForumModel findByTitle (String title);
}
