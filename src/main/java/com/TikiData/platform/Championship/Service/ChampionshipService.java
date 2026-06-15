package com.TikiData.platform.Championship.Service;

import com.TikiData.platform.Championship.DTO.ChampionshipRequestDTO;
import com.TikiData.platform.Championship.DTO.ChampionshipResponseDTO;
import com.TikiData.platform.Championship.Mapper.ChampionshipMapper;
import com.TikiData.platform.Championship.Model.ChampionshipModel;
import com.TikiData.platform.Championship.Repository.ChampionshipRepository;
import com.TikiData.platform.Common.Exception.ResourceNotFoundException;
import com.TikiData.platform.Team.DTO.TeamRequestDTO;
import com.TikiData.platform.Team.Model.TeamModel;
import com.TikiData.platform.Team.Repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class ChampionshipService implements IChampionshipService {
    private final ChampionshipMapper championshipMapper;
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;

    @Transactional
    @Override
    public ChampionshipResponseDTO saveChampionship(ChampionshipRequestDTO championshipRequestDTO) {
        ChampionshipModel championship = championshipMapper.toEntity(championshipRequestDTO);
        return championshipMapper.toDTO(championshipRepository.save(championship));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChampionshipResponseDTO> findAllChampionships() {
        return championshipRepository.findAll()
                .stream()
                .map(championshipMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public ChampionshipResponseDTO updateChampionship(ChampionshipRequestDTO championshipRequestDTO, Long id) throws ResourceNotFoundException {
        ChampionshipModel championship = championshipRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Championship not found with id: " + id));
        championship.setName(championshipRequestDTO.getName());
        championship.setSeason(championshipRequestDTO.getSeason());
        championship.setCountry(championshipRequestDTO.getCountry());
        championship.setStartDate(championshipRequestDTO.getStartDate());
        championship.setEndDate(championshipRequestDTO.getEndDate());
        championship.setNumberOfTeams(championshipRequestDTO.getNumberOfTeams());

        return championshipMapper.toDTO(championshipRepository.save(championship));
    }

    @Transactional
    @Override
    public void deleteChampionshipById(Long id) throws ResourceNotFoundException {
        ChampionshipModel championship = championshipRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Championship not found"));
        championshipRepository.delete(championship);
    }

    @Transactional(readOnly = true)
    @Override
    public ChampionshipResponseDTO findByName(String name) {
        ChampionshipModel championship = championshipRepository.findByName(name);
        return championshipMapper.toDTO(championship);
    }

    @Transactional
    @Override
    public ChampionshipResponseDTO addTeamToChampionship(Long championshipId, Long teamId) throws ResourceNotFoundException {
        ChampionshipModel championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado"));

        TeamModel team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));

        team.setChampionship(championship);
        championship.getTeams().add(team);
        championship.setNumberOfTeams(championship.getNumberOfTeams() + 1);

        return championshipMapper.toDTO(championshipRepository.save(championship));
    }

    @Transactional
    @Override
    public void removeTeamFromChampionship(Long idTeam, Long idChampionship) {
        ChampionshipModel championship = championshipRepository.findById(idChampionship)
                .orElseThrow(() -> new ResourceNotFoundException("Championship no encontrado"));

        boolean removed = championship.getTeams()
                .removeIf(team -> team.getId().equals(idTeam));

        if (!removed) {
            throw new ResourceNotFoundException("El team no forma parte de este campeonato");
        }

        championship.setNumberOfTeams(championship.getNumberOfTeams() - 1);
        championshipRepository.save(championship);
    }
}
