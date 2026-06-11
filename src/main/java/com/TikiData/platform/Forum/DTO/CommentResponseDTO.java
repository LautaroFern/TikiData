package com.TikiData.platform.Forum.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;

    private Long authorId;
    private String authorName;

    private Long forumId;
}
