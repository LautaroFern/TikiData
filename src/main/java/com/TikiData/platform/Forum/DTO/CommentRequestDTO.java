package com.TikiData.platform.Forum.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {

    @NotBlank(message = "El contenido del comentario es obligatorio")
    @Size(min = 1, max = 500, message = "El comentario debe tener entre 1 y 500 caracteres")
    private String content;

    @NotNull(message = "El ID del autor es obligatorio")
    @Positive(message = "El ID del autor debe ser un número positivo")
    private Long authorId;

    private Long forumId;

}
