package com.TikiData.platform.Team.Service;

import com.TikiData.platform.Championship.Model.ChampionshipModel;
import com.TikiData.platform.Championship.Repository.ChampionshipRepository;
import com.TikiData.platform.Common.Exception.InvalidParamException;
import com.TikiData.platform.Common.Exception.ResourceNotFoundException;
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
    @Transactional(readOnly = true)
    public List<PlayerResponseDTO> listPlayers(String name) {
        TeamResponseDTO teamResponseDTO = findTeamByName(name);
        return teamResponseDTO.getPlayers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamResponseDTO> findAllTeams() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TeamResponseDTO findTeamByName(String name) {
        if (name.isBlank()){
            throw new InvalidParamException("El nombre del equipo no puede estar vacio");
        }
        TeamModel teamModel = teamRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
        return teamMapper.toDTO(teamModel);
    }

    @Override
    @Transactional
    public TeamResponseDTO saveTeam(TeamRequestDTO teamRequestDTO) {
        ChampionshipModel championship = championshipRepository.findById(teamRequestDTO.getChampionshipId())
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado con el ID: " + teamRequestDTO.getChampionshipId()));

        TeamModel teamModel = teamMapper.toEntity(teamRequestDTO);
        teamModel.setChampionship(championship);
        return teamMapper.toDTO(teamRepository.save(teamModel));
    }

    @Override
    @Transactional
    public TeamResponseDTO updateTeam(Long id, TeamRequestDTO teamRequestDTO) throws ResourceNotFoundException {
        TeamModel teamModel = teamRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Equipo no encontrado con el ID: " + id)
        );

        ChampionshipModel championship = championshipRepository.findById(teamRequestDTO.getChampionshipId())
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado con el ID: " + teamRequestDTO.getChampionshipId()));

        teamMapper.updateTeamFromDTO(teamRequestDTO, teamModel);
        teamModel.setChampionship(championship);
        return teamMapper.toDTO(teamRepository.save(teamModel));
    }

    @Override
    @Transactional
    public void deleteTeam(Long id) {
        TeamModel teamModel = teamRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Equipo no encontrado con el ID: " + id)
        );
        teamRepository.delete(teamModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamResponseDTO> filterTeams(String name, String country) {
        return teamRepository.searchTeamsByFilters(name, country)
                .stream()
                .map(teamMapper::toDTO)
                .toList();
    }
}