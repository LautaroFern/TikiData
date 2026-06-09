package com.TikiData.platform.Team.Service;

import com.TikiData.platform.Common.Exception.TeamNotFoundException;
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
        TeamModel teamModel = teamMapper.toEntity(teamRequestDTO);
        return teamMapper.toDTO(teamRepository.save(teamModel));
    }

    @Transactional
    public TeamResponseDTO updateTeam(Long id,TeamRequestDTO teamRequestDTO) throws TeamNotFoundException {
        TeamModel teamModel = teamRepository.findById(id).orElseThrow(
                () -> new TeamNotFoundException("Team with id: " + id + " not found")
        );
        teamModel.setName(teamRequestDTO.getName());
        teamModel.setNickname(teamRequestDTO.getNickname());
        teamModel.setCountry(teamRequestDTO.getCountry());
        teamModel.setPresident(teamRequestDTO.getPresident());
        teamModel.setStadium(teamRequestDTO.getStadium());
        teamModel.setFoundationDate(teamRequestDTO.getFoundationDate());
        return teamMapper.toDTO(teamRepository.save(teamModel));
    }

    @Transactional
    public void deleteTeam(Long id) {
        TeamModel teamModel = teamRepository.findById(id).orElseThrow(
                () -> new TeamNotFoundException("Team with id: " + id + " not found")
        );
        teamRepository.delete(teamModel);
    }
}
