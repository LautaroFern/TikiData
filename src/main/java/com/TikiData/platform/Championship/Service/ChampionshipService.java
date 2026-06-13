package com.TikiData.platform.Championship.Service;

import com.TikiData.platform.Championship.DTO.ChampionshipRequestDTO;
import com.TikiData.platform.Championship.DTO.ChampionshipResponseDTO;
import com.TikiData.platform.Championship.Mapper.ChampionshipMapper;
import com.TikiData.platform.Championship.Model.Championship;
import com.TikiData.platform.Championship.Repository.ChampionshipRepository;
import com.TikiData.platform.Common.Exception.ResourceNotFoundException;
import com.TikiData.platform.Team.DTO.TeamRequestDTO;
import com.TikiData.platform.Team.Model.TeamModel;
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

    @Transactional
    @Override
    public ChampionshipResponseDTO saveChampionship(ChampionshipRequestDTO championshipRequestDTO) {
        Championship championship = championshipMapper.toEntity(championshipRequestDTO);
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
        Championship championship = championshipRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Championship not found with id: " + id));
        championship.setName(championshipRequestDTO.getName());
        championship.setSeason(championshipRequestDTO.getSeason());
        championship.setCountry(championshipRequestDTO.getCountry());
        championship.setStartDate(championshipRequestDTO.getStartDate());
        championship.setEndDate(championshipRequestDTO.getEndDate());
        championship.setCantidadEquipos(championshipRequestDTO.getCantidadEquipos());

        return championshipMapper.toDTO(championshipRepository.save(championship));
    }

    @Transactional
    @Override
    public void deleteChampionshipById(Long id) throws ResourceNotFoundException {
        Championship championship = championshipRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Championship not found"));
        championshipRepository.delete(championship);
    }

    @Transactional(readOnly = true)
    @Override
    public ChampionshipResponseDTO findByName(String name) {
        Championship championship = championshipRepository.findByChampionshipName(name);
        return championshipMapper.toDTO(championship);
    }

    @Transactional
    @Override
    public ChampionshipResponseDTO addTeamToChampionship(Long id, TeamRequestDTO teamRequestDTO) throws ResourceNotFoundException {
        Championship championship = championshipRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Championship not found"));

        TeamModel team = new TeamModel();
        team.setName(teamRequestDTO.getName());
        team.setCountry(teamRequestDTO.getCountry());
        team.setFoundationDate(teamRequestDTO.getFoundationDate());
        team.setStadium(teamRequestDTO.getStadium());
        team.setPresident(teamRequestDTO.getPresident());
        team.setNickname(teamRequestDTO.getNickname());
        team.setChampionship(championship);

        championship.getListTeams().add(team);
        championship.setCantidadEquipos(championship.getCantidadEquipos() + 1);

        return championshipMapper.toDTO(championshipRepository.save(championship));
    }

    @Transactional
    @Override
    public void removeTeamFromChampionship(Long idTeam, Long idChampionship) {
        TeamModel teamModel = new TeamModel();
        teamModel.setId(idTeam);

        Championship championship = championshipRepository.findById(idChampionship).orElseThrow(() -> new ResourceNotFoundException("Championship no encontrado"));

        if (championship.getListTeams().contains(teamModel)) {
            championship.getListTeams().remove(teamModel);
            championship.setCantidadEquipos(championship.getCantidadEquipos() - 1);
            championshipRepository.save(championship);
        } else {
            throw new ResourceNotFoundException("El team no forma parte de este campeonato");
        }

    }
}
