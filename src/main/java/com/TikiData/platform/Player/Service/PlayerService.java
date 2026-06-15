package com.TikiData.platform.Player.Service;

import com.TikiData.platform.Common.Exception.PlayerNotFoundException;
import com.TikiData.platform.Common.Exception.TeamNotFoundException;
import com.TikiData.platform.Player.DTO.PlayerRequestDTO;
import com.TikiData.platform.Player.DTO.PlayerResponseDTO;
import com.TikiData.platform.Player.Mapper.PlayerMapper;
import com.TikiData.platform.Player.Model.PlayerModel;
import com.TikiData.platform.Player.Repository.PlayerRepository;
import com.TikiData.platform.Team.Model.TeamModel;
import com.TikiData.platform.Team.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;

    @Override
    public List<PlayerResponseDTO> findAll() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDTO)
                .toList();
    }

    @Override
    public PlayerResponseDTO findById(Long id) throws PlayerNotFoundException {
        return playerMapper.toDTO(playerRepository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException("Player not found with id " + id)
        ));
    }

    @Override
    public PlayerResponseDTO findByName(String name) throws PlayerNotFoundException {
        PlayerModel playerModel = playerRepository.findByName(name);
        if (playerModel == null){
            throw new PlayerNotFoundException("Jugador no encontrado con el nombre: " + name);
        }
        return playerMapper.toDTO(playerModel);
    }

    @Override
    @Transactional
    public PlayerResponseDTO savePlayer(PlayerRequestDTO playerRequestDTO) {
        PlayerModel playerModel = playerMapper.toEntity(playerRequestDTO);
        return playerMapper.toDTO(playerRepository.save(playerModel));
    }

    @Override
    @Transactional
    public PlayerResponseDTO updatePlayer(PlayerRequestDTO playerRequestDTO, Long id) {

        PlayerModel playerModel = playerRepository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException("Player not found with id " + id)
        );

        TeamModel teamModel = teamRepository.findById(playerRequestDTO.getTeamId()).orElseThrow(
                () -> new TeamNotFoundException("Team not found")
        );
        playerModel.setName(playerRequestDTO.getName());
        playerModel.setNumber(playerRequestDTO.getNumber());
        playerModel.setPosition(playerRequestDTO.getPosition());
        playerModel.setBornDate(playerRequestDTO.getBornDate());
        playerModel.setTeam(teamModel);

        return playerMapper.toDTO(playerRepository.save(playerModel));
    }

    @Override
    @Transactional
    public void deletePlayer(Long id) {
        PlayerModel  playerModel = playerRepository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException("Player not found with id " + id)
        );
        playerRepository.delete(playerModel);
    }
}
