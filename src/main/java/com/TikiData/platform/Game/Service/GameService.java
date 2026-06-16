package com.TikiData.platform.Game.Service;

import com.TikiData.platform.Championship.Model.ChampionshipModel;
import com.TikiData.platform.Championship.Repository.ChampionshipRepository;
import com.TikiData.platform.Common.Exception.InvalidParamException;
import com.TikiData.platform.Common.Exception.ResourceNotFoundException;
import com.TikiData.platform.Game.DTO.GameEventRequestDTO;
import com.TikiData.platform.Game.DTO.GameEventResponseDTO;
import com.TikiData.platform.Game.DTO.GameRequestDTO;
import com.TikiData.platform.Game.DTO.GameResponseDTO;
import com.TikiData.platform.Game.Mapper.GameEventMapper;
import com.TikiData.platform.Game.Mapper.GameMapper;
import com.TikiData.platform.Game.Model.EventType;
import com.TikiData.platform.Game.Model.GameEventModel;
import com.TikiData.platform.Game.Model.GameModel;
import com.TikiData.platform.Game.Model.GameStatus;
import com.TikiData.platform.Game.Repository.GameEventRepository;
import com.TikiData.platform.Game.Repository.GameRepository;
import com.TikiData.platform.Player.Model.PlayerModel;
import com.TikiData.platform.Player.Repository.PlayerRepository;
import com.TikiData.platform.Team.Model.TeamModel;
import com.TikiData.platform.Team.Repository.TeamRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService implements IGameService {

    private final GameRepository gameRepository;
    private final GameEventRepository gameEventRepository;
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final GameMapper gameMapper;
    private final GameEventMapper gameEventMapper;

    @Transactional
    @Override
    public GameResponseDTO saveGame(GameRequestDTO dto) {
        if (dto.getHomeTeamId().equals(dto.getAwayTeamId())) {
            throw new InvalidParamException("Un equipo no puede jugar contra sí mismo");
        }

        ChampionshipModel championship = championshipRepository.findById(dto.getChampionshipId())
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado con el ID: " + dto.getChampionshipId()));

        TeamModel homeTeam = teamRepository.findById(dto.getHomeTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Equipo local no encontrado con el ID: " + dto.getHomeTeamId()));

        TeamModel awayTeam = teamRepository.findById(dto.getAwayTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Equipo visitante no encontrado con el ID: " + dto.getAwayTeamId()));

        if (homeTeam.getChampionship() == null || !homeTeam.getChampionship().getId().equals(championship.getId())) {
            throw new InvalidParamException("El equipo local no pertenece a este campeonato");
        }

        if (awayTeam.getChampionship() == null || !awayTeam.getChampionship().getId().equals(championship.getId())) {
            throw new InvalidParamException("El equipo visitante no pertenece a este campeonato");
        }

        GameModel game = gameMapper.toEntity(dto);
        game.setChampionship(championship);
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);

        return gameMapper.toDTO(gameRepository.save(game));
    }


    @Transactional(readOnly = true)
    @Override
    public List<GameResponseDTO> findAllGames() {
        return gameRepository.findAll().stream()
                .map(gameMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<GameResponseDTO> findByChampionship(Long championshipId){
        return gameRepository.findByChampionshipId(championshipId)
                .stream()
                .map(gameMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<GameResponseDTO> findByTeam(Long teamId){
        return gameRepository.findByHomeTeamIdOrAwayTeamId(teamId, teamId)
                .stream()
                .map(gameMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public GameResponseDTO findGameById(Long id) {
        GameModel game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con el ID: " + id));
        return gameMapper.toDTO(game);
    }

    @Transactional
    @Override
    public GameResponseDTO updateGameStatus(Long id, String status, Integer homeGoals, Integer awayGoals, Integer homePenalties, Integer awayPenalties) {
        GameModel game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con el ID: " + id));

        game.setStatus(GameStatus.valueOf(status.toUpperCase()));
        game.setHomeGoals(homeGoals);
        game.setAwayGoals(awayGoals);
        game.setHomePenalties(homePenalties);
        game.setAwayPenalties(awayPenalties);

        return gameMapper.toDTO(gameRepository.save(game));
    }

    @Transactional
    @Override
    public GameEventResponseDTO addEventToGame(Long gameId, GameEventRequestDTO dto) {
        GameModel game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con el ID: " + gameId));

        PlayerModel primaryPlayer = playerRepository.findById(dto.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con el ID: " + dto.getPlayerId()));

        PlayerModel secondaryPlayer = null;
        if (dto.getSecondaryPlayerId() != null) {
            secondaryPlayer = playerRepository.findById(dto.getSecondaryPlayerId())
                    .orElseThrow(() -> new RuntimeException("Jugador secundario no encontrado con el ID: " + dto.getSecondaryPlayerId()));
        }

        GameEventModel event = GameEventModel.builder()
                .game(game)
                .minute(dto.getMinute())
                .type(EventType.valueOf(dto.getType().toUpperCase()))
                .player(primaryPlayer)
                .secondaryPlayer(secondaryPlayer)
                .build();

        return gameEventMapper.toDTO(gameEventRepository.save(event));
    }
}
