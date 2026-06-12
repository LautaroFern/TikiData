package com.TikiData.platform.Forum.Service;

import com.TikiData.platform.Forum.DTO.CommentRequestDTO;
import com.TikiData.platform.Forum.DTO.CommentResponseDTO;
import com.TikiData.platform.Forum.Mapper.CommentMapper;
import com.TikiData.platform.Forum.Model.CommentModel;
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
        CommentModel commentModel = commentMapper.toEntity(commentRequestDTO);
        return commentMapper.toResponse(commentRepository.save(commentModel));
    }

    @Override
    public void deleteComment(Long id) {
        CommentModel commentModel = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        commentRepository.delete(commentModel);
    }
}
