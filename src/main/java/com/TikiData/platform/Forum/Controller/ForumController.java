package com.TikiData.platform.Forum.Controller;

import com.TikiData.platform.Forum.DTO.CommentRequestDTO;
import com.TikiData.platform.Forum.DTO.CommentResponseDTO;
import com.TikiData.platform.Forum.DTO.ForumRequestDTO;
import com.TikiData.platform.Forum.DTO.ForumResponseDTO;
import com.TikiData.platform.Forum.Service.ForumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
@RequiredArgsConstructor
public class ForumController {

    private final ForumService forumService;

    @PostMapping
    public ResponseEntity<ForumResponseDTO> createForum(@RequestBody @Valid ForumRequestDTO dto) {
        return new ResponseEntity<>(forumService.createForum(dto), HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<ForumResponseDTO> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(forumService.searchByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForum(@PathVariable Long id) {
        forumService.deleteForum(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{forumId}/comments")
    public ResponseEntity<ForumResponseDTO> addComment(
            @PathVariable Long forumId,
            @RequestBody @Valid CommentRequestDTO dto) {
        dto.setForumId(forumId);
        return new ResponseEntity<>(forumService.addComment(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{forumId}/comments")
    public ResponseEntity<List<CommentResponseDTO>> listComments(@PathVariable Long forumId) {
        return ResponseEntity.ok(forumService.listComments(forumId));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        forumService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

}
