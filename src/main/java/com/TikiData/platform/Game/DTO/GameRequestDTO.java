package com.TikiData.platform.Game.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameRequestDTO {
    @NotNull(message = "La fecha y hora del partido es obligatoria")
    @FutureOrPresent(message = "El partido no puede programarse en el pasado")
    private LocalDateTime gameDate;

    @NotBlank(message = "El estadio es obligatorio")
    private String stadium;

    @NotNull(message = "El ID del torneo es obligatorio")
    private Long championshipId;

    @NotNull(message = "El ID del equipo local es obligatorio")
    private Long homeTeamId;

    @NotNull(message = "El ID del equipo visitante es obligatorio")
    private Long awayTeamId;
}
