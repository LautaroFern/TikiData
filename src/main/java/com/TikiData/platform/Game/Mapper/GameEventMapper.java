package com.TikiData.platform.Game.Mapper;

import com.TikiData.platform.Game.DTO.GameEventResponseDTO;
import com.TikiData.platform.Game.Model.GameEventModel;
import org.springframework.stereotype.Component;

@Component

public class GameEventMapper {

    public GameEventResponseDTO toDTO(GameEventModel event) {
        GameEventResponseDTO dto = new GameEventResponseDTO();
        dto.setId(event.getId());
        dto.setMinute(event.getMinute());
        dto.setType(event.getType().name());

        if (event.getPlayer() != null) {
            dto.setPlayerId(event.getPlayer().getId());
            dto.setPlayerName(event.getPlayer().getName());
        }

        if (event.getSecondaryPlayer() != null) {
            dto.setSecondaryPlayerId(event.getSecondaryPlayer().getId());
            dto.setSecondaryPlayerName(event.getSecondaryPlayer().getName());
        }

        return dto;
    }
}
