package com.TikiData.platform.Game.Service;

import com.TikiData.platform.Game.DTO.GameEventRequestDTO;
import com.TikiData.platform.Game.DTO.GameEventResponseDTO;
import com.TikiData.platform.Game.DTO.GameRequestDTO;
import com.TikiData.platform.Game.DTO.GameResponseDTO;

import java.util.List;

public interface IGameService {
    GameResponseDTO saveGame(GameRequestDTO dto);
    List<GameResponseDTO> findAllGames();
    GameResponseDTO findGameById(Long id);
    GameResponseDTO updateGameStatus(Long id, String status, Integer homeGoals, Integer awayGoals);
    GameEventResponseDTO addEventToGame(Long gameId, GameEventRequestDTO dto);

}
