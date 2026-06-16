package com.TikiData.platform.Game.Mapper;

import com.TikiData.platform.Game.DTO.GameEventResponseDTO;
import com.TikiData.platform.Game.DTO.GameRequestDTO;
import com.TikiData.platform.Game.DTO.GameResponseDTO;
import com.TikiData.platform.Game.Model.GameEventModel;
import com.TikiData.platform.Game.Model.GameModel;
import com.TikiData.platform.Game.Model.GameStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GameMapper {

    private final GameEventMapper gameEventMapper;


    public GameModel toEntity(GameRequestDTO dto) {
        GameModel game = new GameModel();
        game.setGameDate(dto.getGameDate());
        game.setStadium(dto.getStadium());
        game.setStatus(GameStatus.SCHEDULED);
        game.setHomeGoals(null);
        game.setAwayGoals(null);

        return game;
    }

    public GameResponseDTO toDTO(GameModel game) {
        GameResponseDTO dto = new GameResponseDTO();
        dto.setId(game.getId());
        dto.setGameDate(game.getGameDate());
        dto.setStadium(game.getStadium());
        dto.setStatus(game.getStatus().name());
        dto.setHomeGoals(game.getHomeGoals());
        dto.setAwayGoals(game.getAwayGoals());

        if (game.getChampionship() != null) {
            dto.setChampionshipId(game.getChampionship().getId());
            dto.setChampionshipName(game.getChampionship().getName());
        }

        if (game.getHomeTeam() != null) {
            dto.setHomeTeamId(game.getHomeTeam().getId());
            dto.setHomeTeamName(game.getHomeTeam().getName());
        }
        if (game.getAwayTeam() != null) {
            dto.setAwayTeamId(game.getAwayTeam().getId());
            dto.setAwayTeamName(game.getAwayTeam().getName());
        }
        dto.setEvents(game.getEvents() != null
                ? game.getEvents().stream()
                .map(gameEventMapper::toDTO)
                .toList()
                : List.of());

        return dto;
    }
}
