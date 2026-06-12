package com.TikiData.platform.Team.Service;

import com.TikiData.platform.Championship.Model.Championship;
import com.TikiData.platform.Championship.Repository.ChampionshipRepository;
import com.TikiData.platform.Common.Exception.ChampionshipNotFoundException;
import com.TikiData.platform.Common.Exception.TeamNotFoundException;
import com.TikiData.platform.Player.DTO.PlayerResponseDTO;
import com.TikiData.platform.Team.DTO.TeamRequestDTO;
import com.TikiData.platform.Team.DTO.TeamResponseDTO;
import com.TikiData.platform.Team.Mapper.TeamMapper;
import com.TikiData.platform.Team.Model.TeamModel;
import com.TikiData.platform.Team.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final ChampionshipRepository championshipRepository;

    @Override
    public List<PlayerResponseDTO> listPlayers() {
        return List.of();
    }

    @Override
    public List<TeamResponseDTO> findAllTeams() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toDTO)
                .toList();
    }

    @Override
    public TeamResponseDTO findTeamByName(String name) {
        TeamModel teamModel = teamRepository.findByTeamName(name);
        return teamMapper.toDTO(teamModel);
    }

    @Transactional
    public TeamResponseDTO saveTeam(TeamRequestDTO teamRequestDTO) {
        Championship championship = championshipRepository.findById(teamRequestDTO.getChampionshipId())
                .orElseThrow(() -> new ChampionshipNotFoundException("Campeonato no encontrado con el ID: " + teamRequestDTO.getChampionshipId()));

        TeamModel teamModel = teamMapper.toEntity(teamRequestDTO);
        teamModel.setChampionship(championship);
        return teamMapper.toDTO(teamRepository.save(teamModel));
    }

    @Transactional
    public TeamResponseDTO updateTeam(Long id, TeamRequestDTO teamRequestDTO) throws TeamNotFoundException {
        TeamModel teamModel = teamRepository.findById(id).orElseThrow(
                () -> new TeamNotFoundException("Equipo no encontrado con el ID: " + id)
        );

        Championship championship = championshipRepository.findById(teamRequestDTO.getChampionshipId())
                .orElseThrow(() -> new ChampionshipNotFoundException("Campeonato no encontrado con el ID: " + teamRequestDTO.getChampionshipId()));

        teamMapper.updateTeamFromDTO(teamRequestDTO, teamModel);
        teamModel.setChampionship(championship);
        return teamMapper.toDTO(teamRepository.save(teamModel));
    }

    @Transactional
    public void deleteTeam(Long id) {
        TeamModel teamModel = teamRepository.findById(id).orElseThrow(
                () -> new TeamNotFoundException("Equipo no encontrado con el ID: " + id)
        );
        teamRepository.delete(teamModel);
    }

    @Override
    public List<TeamResponseDTO> filterTeams(String name, String country) {
        return teamRepository.searchTeamsByFilters(name, country)
                .stream()
                .map(teamMapper::toDTO)
                .toList();
    }
}