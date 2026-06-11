package com.TikiData.platform.Player.Service;

import com.TikiData.platform.Common.Exception.ChampionshipNotFoundException;
import com.TikiData.platform.Common.Exception.PlayerNotFoundException;
import com.TikiData.platform.Player.DTO.PlayerRequestDTO;
import com.TikiData.platform.Player.DTO.PlayerResponseDTO;

import java.util.List;

public interface IPlayerService {
    List<PlayerResponseDTO> findAll();

    PlayerResponseDTO findById(Long id)throws PlayerNotFoundException;

    PlayerResponseDTO findByName(String name)throws PlayerNotFoundException;

    PlayerResponseDTO savePlayer(PlayerRequestDTO playerRequestDTO);

    PlayerResponseDTO updatePlayer(PlayerRequestDTO playerRequestDTO, Long id);

    void deletePlayer(Long id);
}
