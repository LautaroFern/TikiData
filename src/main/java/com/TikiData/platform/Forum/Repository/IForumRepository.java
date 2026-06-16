package com.TikiData.platform.Forum.Repository;

import com.TikiData.platform.Forum.Model.ForumModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IForumRepository extends JpaRepository<ForumModel, Long> {
    Optional<ForumModel> findByTitle (String title);
}
