package com.TikiData.platform.Forum.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForumResponseDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Long creatorId;
    private String creatorName;
    private List<CommentResponseDTO> comments;
    private Integer totalComments;
}
