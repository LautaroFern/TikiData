package com.TikiData.platform.Forum.Service;

import com.TikiData.platform.Common.Exception.ResourceNotFoundException;
import com.TikiData.platform.Forum.DTO.CommentRequestDTO;
import com.TikiData.platform.Forum.DTO.CommentResponseDTO;
import com.TikiData.platform.Forum.DTO.ForumRequestDTO;
import com.TikiData.platform.Forum.DTO.ForumResponseDTO;
import com.TikiData.platform.Forum.Mapper.CommentMapper;
import com.TikiData.platform.Forum.Mapper.ForumMapper;
import com.TikiData.platform.Forum.Model.CommentModel;
import com.TikiData.platform.Forum.Model.ForumModel;
import com.TikiData.platform.Forum.Repository.ICommentRepository;
import com.TikiData.platform.Forum.Repository.IForumRepository;
import com.TikiData.platform.User.Model.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ForumService implements IForumService {
    private final ForumMapper forumMapper;
    private final IForumRepository forumRepository;
    private final ICommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public ForumResponseDTO createForum(ForumRequestDTO forumRequestDTO) {
        ForumModel forumModel = forumMapper.toEntity(forumRequestDTO);
        return forumMapper.toResponse(forumRepository.save(forumModel));
    }

    @Override
    public void deleteForum(Long id) {
        ForumModel forumModel = forumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Foro no encontrado"));
        forumRepository.delete(forumModel);
    }

    @Override
    public ForumResponseDTO searchByName(String name) {
        ForumModel forumModel = forumRepository.findByName(name);
        return forumMapper.toResponse(forumModel);
    }

    @Override
    public ForumResponseDTO addComment(CommentRequestDTO commentRequestDTO) {
        ForumModel forumModel = forumRepository.findById(commentRequestDTO.getForumId()).orElseThrow(() -> new ResourceNotFoundException("Foro no encontrado"));

        CommentModel commentModel = new CommentModel();
        commentModel.setContent(commentRequestDTO.getContent());
        commentModel.setCreatedAt(LocalDateTime.now());
        commentModel.setForum(forumModel);

        UserModel user = new UserModel();
        user.setId(commentRequestDTO.getAuthorId());
        commentModel.setAuthor(user);

        forumModel.getComments().add(commentModel);
        return forumMapper.toResponse(forumRepository.save(forumModel));
    }

    @Override
    public void deleteComment(Long id) {
        CommentModel commentModel = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));
        ForumModel forumModel = forumRepository.findById(commentModel.getForum().getId()).orElseThrow(() -> new ResourceNotFoundException("Foro no encontrado"));
        forumModel.getComments().remove(commentModel);
    }

    @Override
    public List<CommentResponseDTO> listComments(Long id) {
        ForumModel forumModel = forumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Foro no encontrado"));
        return forumModel.getComments()
                .stream()
                .map(commentMapper::toResponse)
                .toList();
    }
}
