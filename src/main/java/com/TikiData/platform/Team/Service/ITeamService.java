package com.TikiData.platform.Team.Service;

import com.TikiData.platform.Player.DTO.PlayerResponseDTO;
import com.TikiData.platform.Team.DTO.TeamRequestDTO;
import com.TikiData.platform.Team.DTO.TeamResponseDTO;

import java.util.List;

public interface ITeamService {
    List<TeamResponseDTO> findAllTeams();
    TeamResponseDTO findTeamByName(String name);
    TeamResponseDTO saveTeam(TeamRequestDTO teamRequestDTO);
    TeamResponseDTO updateTeam(Long id, TeamRequestDTO teamRequestDTO);
    void deleteTeam(Long id);    List<TeamResponseDTO> filterTeams(String name, String country);
    List<PlayerResponseDTO> listPlayers(String name);
}
