package com.TikiData.platform.Championship.Service;

import com.TikiData.platform.Championship.DTO.ChampionshipRequestDTO;
import com.TikiData.platform.Championship.DTO.ChampionshipResponseDTO;
import com.TikiData.platform.Championship.Mapper.ChampionshipMapper;
import com.TikiData.platform.Championship.Model.Championship;
import com.TikiData.platform.Championship.Repository.ChampionshipRepository;
import com.TikiData.platform.Common.Exception.ChampionshipNotFoundException;
import com.TikiData.platform.Team.DTO.TeamRequestDTO;
import com.TikiData.platform.Team.Mapper.TeamMapper;
import com.TikiData.platform.Team.Model.TeamModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChampionshipService implements IChampionshipService {
    private final ChampionshipMapper championshipMapper;
    private final ChampionshipRepository championshipRepository;

    @Override
    public ChampionshipResponseDTO saveChampionship(ChampionshipRequestDTO championshipRequestDTO) {
        Championship championship = championshipMapper.toEntity(championshipRequestDTO);
        return championshipMapper.toDTO(championshipRepository.save(championship));
    }

    @Override
    public List<ChampionshipResponseDTO> findAllChampionships() {
        return championshipRepository.findAll()
                .stream()
                .map(championshipMapper::toDTO)
                .toList();
    }

    @Override
    public ChampionshipResponseDTO updateChampionship(ChampionshipRequestDTO championshipRequestDTO, Long id) throws ChampionshipNotFoundException {
        Championship championship = championshipRepository.findById(id).orElseThrow(() -> new ChampionshipNotFoundException("Championship not found with id: " + id));
        championship.setName(championshipRequestDTO.getName());
        championship.setSeason(championshipRequestDTO.getSeason());
        championship.setCountry(championshipRequestDTO.getCountry());
        championship.setStartDate(championshipRequestDTO.getStartDate());
        championship.setEndDate(championshipRequestDTO.getEndDate());
        championship.setCantidadEquipos(championshipRequestDTO.getCantidadEquipos());
        return championshipMapper.toDTO(championshipRepository.save(championship));
    }

    @Override
    public void deleteChampionshipById(Long id) throws ChampionshipNotFoundException {
        Championship championship = championshipRepository.findById(id).orElseThrow(() -> new ChampionshipNotFoundException("Championship not found"));
        championshipRepository.delete(championship);
    }

    @Override
    public ChampionshipResponseDTO findByName(String name) {
        Championship championship = championshipRepository.findByChampionshipName(name);
        return championshipMapper.toDTO(championship);
    }

    @Override
    public ChampionshipResponseDTO addTeamToChampionship(Long id, TeamRequestDTO teamRequestDTO) throws ChampionshipNotFoundException {
        Championship championship = championshipRepository.findById(id).orElseThrow(() -> new ChampionshipNotFoundException("Championship not found"));
        TeamModel team = new TeamModel();
        team.setName(teamRequestDTO.getName());
        team.setCountry(teamRequestDTO.getCountry());
        team.setFoundationDate(teamRequestDTO.getFoundationName());
        team.setStadium(teamRequestDTO.getStadium());
        team.setPresident(teamRequestDTO.getPresident());
        team.setNickname(teamRequestDTO.getNickname());
        team.setChampionship(championship);
        championship.getListTeams().add(team);
        return championshipMapper.toDTO(championshipRepository.save(championship));
    }
}
