package com.TikiData.platform.Championship.Mapper;

import com.TikiData.platform.Championship.DTO.ChampionshipRequestDTO;
import com.TikiData.platform.Championship.DTO.ChampionshipResponseDTO;
import com.TikiData.platform.Championship.Model.Championship;
import com.TikiData.platform.Team.Model.TeamModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ChampionshipMapper {
    public Championship toEntity(ChampionshipRequestDTO championshipRequestDTO) {
        Championship championship = new Championship();
        championship.setName(championshipRequestDTO.getName());
        championship.setCountry(championshipRequestDTO.getCountry());
        championship.setCantidadEquipos(championshipRequestDTO.getCantidadEquipos());
        championship.setStartDate(championshipRequestDTO.getStartDate());
        championship.setEndDate(championshipRequestDTO.getEndDate());
        championship.setSeason(championshipRequestDTO.getSeason());
        return championship;
    }

    public ChampionshipResponseDTO toDTO(Championship championship) {
        ChampionshipResponseDTO championshipResponseDTO = new ChampionshipResponseDTO();
        championshipResponseDTO.setId(championship.getId());
        championshipResponseDTO.setName(championship.getName());
        championshipResponseDTO.setCountry(championship.getCountry());
        championshipResponseDTO.setCantidadEquipos(championship.getCantidadEquipos());
        championshipResponseDTO.setListTeams(championship.getListTeams().stream().map(teamModel -> new TeamModel()).toList());
        championshipResponseDTO.setSeason(championship.getSeason());
        championshipResponseDTO.setEndDate(championship.getEndDate());
        championshipResponseDTO.setStartDate(championship.getStartDate());
        return championshipResponseDTO;
    }
}
