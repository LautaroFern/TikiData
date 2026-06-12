package com.TikiData.platform.Forum.Repository;

import com.TikiData.platform.Forum.Model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<CommentModel, Long> {
}
