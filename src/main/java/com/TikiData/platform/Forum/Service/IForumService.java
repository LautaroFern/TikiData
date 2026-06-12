package com.TikiData.platform.Forum.Service;

import com.TikiData.platform.Forum.DTO.ForumRequestDTO;
import com.TikiData.platform.Forum.DTO.ForumResponseDTO;

public interface IForumService {
    ForumResponseDTO createForum (ForumRequestDTO forumRequestDTO);
    void deleteForum (Long id);
    ForumResponseDTO searchByName (String name);
}
