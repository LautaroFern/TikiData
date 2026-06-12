package com.TikiData.platform.Forum.Service;

import com.TikiData.platform.Forum.DTO.CommentRequestDTO;
import com.TikiData.platform.Forum.DTO.CommentResponseDTO;
import com.TikiData.platform.Forum.Mapper.CommentMapper;
import com.TikiData.platform.Forum.Repository.ICommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService  implements ICommentService{
    private final CommentMapper commentMapper;
    private final ICommentRepository commentRepository;


    @Override
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        return null;
    }

    @Override
    public void deleteComment(Long id) {

    }
}
