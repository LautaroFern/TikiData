package com.TikiData.platform.Forum.Service;

import com.TikiData.platform.Forum.DTO.CommentRequestDTO;
import com.TikiData.platform.Forum.DTO.CommentResponseDTO;

public interface ICommentService {
    CommentResponseDTO createComment (CommentRequestDTO commentRequestDTO);
    void deleteComment (Long id);
}
