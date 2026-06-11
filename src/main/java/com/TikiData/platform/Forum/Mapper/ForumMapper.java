package com.TikiData.platform.Forum.Mapper;

import com.TikiData.platform.Forum.DTO.ForumRequestDTO;
import com.TikiData.platform.Forum.DTO.ForumResponseDTO;
import com.TikiData.platform.Forum.Model.ForumModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ForumMapper {

    private final CommentMapper commentMapper = new CommentMapper();

    public ForumModel toEntity(ForumRequestDTO requestDTO){
        ForumModel forumModel = new ForumModel();
        forumModel.setTitle(requestDTO.getTitle());
        forumModel.setDescription(requestDTO.getDescription());
        forumModel.setCreatedAt(LocalDateTime.now());
        return forumModel;
    }

    public ForumResponseDTO toResponse (ForumModel entity){
        ForumResponseDTO forumResponseDTO = new ForumResponseDTO();
        forumResponseDTO.setId(entity.getId());
        forumResponseDTO.setTitle(entity.getTitle());
        forumResponseDTO.setDescription(entity.getDescription());
        forumResponseDTO.setCreatedAt(entity.getCreatedAt());
        forumResponseDTO.setCreatorId(entity.getCreator().getId());
        forumResponseDTO.setCreatorName(entity.getCreator().getFirstName());
        forumResponseDTO.setTotalComments(entity.getComments() != null
                ? entity.getComments().size()
                :0);
        forumResponseDTO.setComments(entity.getComments() != null
                ? entity.getComments().stream()
                  .map(commentMapper::toResponse)
                  .collect(Collectors.toList())
                : List.of());
        return forumResponseDTO;
    }
}
