package com.TikiData.platform.Game.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameEventRequestDTO {

    @NotNull(message = "El minuto es obligatorio")
    @Min(value = 0, message = "El minuto no puede ser negativo")
    private Integer minute;

    @NotBlank(message = "El tipo de evento es obligatorio")
    private String type;

    @NotNull(message = "El ID del jugador principal es obligatorio")
    private Long playerId;

    private Long secondaryPlayerId;
}
