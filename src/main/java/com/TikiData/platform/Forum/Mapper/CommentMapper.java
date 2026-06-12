package com.TikiData.platform.Forum.Mapper;

import com.TikiData.platform.Forum.DTO.CommentRequestDTO;
import com.TikiData.platform.Forum.DTO.CommentResponseDTO;
import com.TikiData.platform.Forum.Model.CommentModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {

    public CommentModel toEntity(CommentRequestDTO requestDTO){
        CommentModel commentModel = new CommentModel();
        commentModel.setContent(requestDTO.getContent());
        commentModel.setCreatedAt(LocalDateTime.now());
        return commentModel;
    }

    public CommentResponseDTO toResponse(CommentModel entity){
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setId(entity.getId());
        commentResponseDTO.setAuthorId(entity.getAuthor().getId());
        commentResponseDTO.setAuthorName(entity.getAuthor().getFirstName());
        commentResponseDTO.setContent(entity.getContent());
        commentResponseDTO.setCreatedAt(entity.getCreatedAt());
        commentResponseDTO.setForumId(entity.getForum().getId());

        return commentResponseDTO;
    }
}
