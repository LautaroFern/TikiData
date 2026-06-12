package com.TikiData.platform.Forum.Service;

import com.TikiData.platform.Forum.DTO.ForumRequestDTO;
import com.TikiData.platform.Forum.DTO.ForumResponseDTO;
import com.TikiData.platform.Forum.Mapper.ForumMapper;
import com.TikiData.platform.Forum.Model.ForumModel;
import com.TikiData.platform.Forum.Repository.IForumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ForumService implements IForumService {
    private final ForumMapper forumMapper;
    private final IForumRepository forumRepository;

    @Override
    public ForumResponseDTO createForum(ForumRequestDTO forumRequestDTO) {
        ForumModel forumModel = forumMapper.toEntity(forumRequestDTO);
        return forumMapper.toResponse(forumRepository.save(forumModel));
    }

    @Override
    public void deleteForum(Long id) {

    }

    @Override
    public ForumResponseDTO searchByName(String name) {
        return null;
    }
}
