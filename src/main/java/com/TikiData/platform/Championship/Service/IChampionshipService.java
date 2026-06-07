package com.TikiData.platform.Championship.Service;


import com.TikiData.platform.Championship.DTO.ChampionshipRequestDTO;
import com.TikiData.platform.Championship.DTO.ChampionshipResponseDTO;
import com.TikiData.platform.Common.Exception.ChampionshipNotFoundException;
import com.TikiData.platform.Team.DTO.TeamRequestDTO;

import java.util.List;

public interface IChampionshipService {
    ChampionshipResponseDTO saveChampionship(ChampionshipRequestDTO championshipRequestDTO);

    List<ChampionshipResponseDTO> findAllChampionships();

    ChampionshipResponseDTO updateChampionship(ChampionshipRequestDTO championshipRequestDTO, Long id) throws ChampionshipNotFoundException;

    void deleteChampionshipById(Long id) throws ChampionshipNotFoundException;

    ChampionshipResponseDTO findByName(String name);

    ChampionshipResponseDTO addTeamToChampionship(Long id, TeamRequestDTO teamRequestDTO) throws ChampionshipNotFoundException;
}
