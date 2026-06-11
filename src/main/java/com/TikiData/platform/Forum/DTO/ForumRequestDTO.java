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
public class ForumRequestDTO {

    @NotBlank(message = "El titulo es obligatorio")
    @Size(min = 5, max = 150, message = "El titulo debe tener entre 5 y 150 caracteres")
    private String title;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 10, max = 1000, message = "La descripcion debe tener entre 10 y 1000 caracteres")
    private String description;

    @NotNull(message = "El ID del creador es obligatorio")
    @Positive(message = "El ID del creador debe ser un numero positivo")
    private Long creatorId;
}
