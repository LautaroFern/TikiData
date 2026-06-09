package com.TikiData.platform.Team.Mapper;

import com.TikiData.platform.Team.DTO.TeamRequestDTO;
import com.TikiData.platform.Team.DTO.TeamResponseDTO;
import com.TikiData.platform.Team.Model.TeamModel;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public TeamModel toEntity(TeamRequestDTO teamRequestDTO) {
        TeamModel teamModel = new TeamModel();
        teamModel.setName(teamRequestDTO.getName());
        teamModel.setNickname(teamRequestDTO.getNickname());
        teamModel.setStadium(teamRequestDTO.getStadium());
        teamModel.setCountry(teamRequestDTO.getCountry());
        teamModel.setPresident(teamRequestDTO.getPresident());
        teamModel.setFoundationDate(teamRequestDTO.getFoundationDate());
        return teamModel;
    }

    public TeamResponseDTO toDTO(TeamModel teamModel) {
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO();
        teamResponseDTO.setId(teamModel.getId());
        teamResponseDTO.setName(teamModel.getName());
        teamResponseDTO.setNickname(teamModel.getNickname());
        teamResponseDTO.setStadium(teamModel.getStadium());
        teamResponseDTO.setCountry(teamModel.getCountry());
        teamResponseDTO.setPresident(teamModel.getPresident());
        teamResponseDTO.setFoundationDate(teamModel.getFoundationDate());
        return teamResponseDTO;
    }
}
