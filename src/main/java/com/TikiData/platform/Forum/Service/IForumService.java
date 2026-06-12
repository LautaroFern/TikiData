package com.TikiData.platform.Forum.Service;

import com.TikiData.platform.Forum.DTO.CommentRequestDTO;
import com.TikiData.platform.Forum.DTO.CommentResponseDTO;
import com.TikiData.platform.Forum.DTO.ForumRequestDTO;
import com.TikiData.platform.Forum.DTO.ForumResponseDTO;

import java.util.List;

public interface IForumService {
    ForumResponseDTO createForum (ForumRequestDTO forumRequestDTO);
    void deleteForum (Long id);
    ForumResponseDTO searchByName (String name);
    ForumResponseDTO addComment (CommentRequestDTO commentRequestDTO);
    List<CommentResponseDTO> listComments (Long id);
    void deleteComment (Long id);
}
