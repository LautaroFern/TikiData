package com.TikiData.platform.Championship.Mapper;

import com.TikiData.platform.Championship.DTO.ChampionshipRequestDTO;
import com.TikiData.platform.Championship.DTO.ChampionshipResponseDTO;
import com.TikiData.platform.Championship.Model.ChampionshipModel;
import com.TikiData.platform.Team.Mapper.TeamMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ChampionshipMapper {
    private final TeamMapper teamMapper;
    public ChampionshipModel toEntity(ChampionshipRequestDTO championshipRequestDTO) {
        ChampionshipModel championship = new ChampionshipModel();
        championship.setName(championshipRequestDTO.getName());
        championship.setCountry(championshipRequestDTO.getCountry());
        championship.setNumberOfTeams(championshipRequestDTO.getNumberOfTeams());
        championship.setStartDate(championshipRequestDTO.getStartDate());
        championship.setEndDate(championshipRequestDTO.getEndDate());
        championship.setSeason(championshipRequestDTO.getSeason());
        return championship;
    }

    public ChampionshipResponseDTO toDTO(ChampionshipModel championship) {
        ChampionshipResponseDTO dto = new ChampionshipResponseDTO();
        dto.setId(championship.getId());
        dto.setName(championship.getName());
        dto.setCountry(championship.getCountry());
        dto.setNumberOfTeams(championship.getNumberOfTeams());
        dto.setStartDate(championship.getStartDate());
        dto.setEndDate(championship.getEndDate());
        dto.setSeason(championship.getSeason());
        dto.setTeams(championship.getTeams() != null
                ? championship.getTeams().stream()
                .map(teamMapper::toDTO)
                .toList()
                : List.of());
        return dto;
    }
}
