package com.TikiData.platform.Game.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameEventResponseDTO {
    private Long id;
    private Integer minute;
    private String type;
    private Long playerId;
    private String playerName;
    private Long secondaryPlayerId;
    private String secondaryPlayerName;
}
