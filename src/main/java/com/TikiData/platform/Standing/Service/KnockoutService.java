package com.TikiData.platform.Standing.Service;

import com.TikiData.platform.Championship.Model.ChampionshipModel;
import com.TikiData.platform.Championship.Model.TournamentFormat;
import com.TikiData.platform.Championship.Repository.ChampionshipRepository;
import com.TikiData.platform.Common.Exception.InvalidParamException;
import com.TikiData.platform.Common.Exception.ResourceNotFoundException;
import com.TikiData.platform.Game.Model.GameModel;
import com.TikiData.platform.Game.Model.GameStatus;
import com.TikiData.platform.Game.Repository.GameRepository;
import com.TikiData.platform.Standing.DTO.TieDTO;
import com.TikiData.platform.Team.Model.TeamModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KnockoutService implements IKnockoutService {

    private final ChampionshipRepository championshipRepository;
    private final GameRepository gameRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TieDTO> getBracket(Long championshipId) {

        ChampionshipModel championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado con el ID: " + championshipId));

        if (championship.getFormat() != TournamentFormat.KNOCKOUT) {
            throw new InvalidParamException("Este campeonato no es de formato eliminación directa");
        }

        List<GameModel> games = gameRepository.findByChampionshipId(championshipId);

        Map<String, List<GameModel>> gamesByTie = games.stream()
                .filter(g -> g.getTieId() != null)
                .collect(Collectors.groupingBy(GameModel::getTieId));

        List<TieDTO> bracket = new ArrayList<>();

        for (Map.Entry<String, List<GameModel>> entry : gamesByTie.entrySet()) {
            TieDTO tieDTO = buildTieDTO(entry.getKey(), entry.getValue());
            bracket.add(tieDTO);
        }

        bracket.sort(Comparator.comparing(TieDTO::getRound, Comparator.nullsLast(Comparator.naturalOrder())));

        return bracket;
    }

    private TieDTO buildTieDTO(String tieId, List<GameModel> tieGames) {
        TieDTO dto = new TieDTO();
        dto.setTieId(tieId);

        GameModel firstGame = tieGames.get(0);
        dto.setRound(firstGame.getRound() != null ? firstGame.getRound().name() : null);

        TeamModel teamA = firstGame.getHomeTeam();
        TeamModel teamB = firstGame.getAwayTeam();

        dto.setHomeTeamId(teamA.getId());
        dto.setHomeTeamName(teamA.getName());
        dto.setAwayTeamId(teamB.getId());
        dto.setAwayTeamName(teamB.getName());

        dto.setGameIds(tieGames.stream().map(GameModel::getId).toList());

        int aggregateA = 0;
        int aggregateB = 0;
        boolean allFinished = true;

        for (GameModel game : tieGames) {
            if (game.getStatus() != GameStatus.FINISHED || game.getHomeGoals() == null || game.getAwayGoals() == null) {
                allFinished = false;
                continue;
            }

            if (game.getHomeTeam().getId().equals(teamA.getId())) {
                aggregateA += game.getHomeGoals();
                aggregateB += game.getAwayGoals();
            } else {
                aggregateA += game.getAwayGoals();
                aggregateB += game.getHomeGoals();
            }
        }

        dto.setHomeAggregateGoals(aggregateA);
        dto.setAwayAggregateGoals(aggregateB);
        dto.setFinished(allFinished);

        if (allFinished) {
            if (aggregateA > aggregateB) {
                dto.setWinnerTeamId(teamA.getId());
                dto.setWinnerTeamName(teamA.getName());
            } else if (aggregateB > aggregateA) {
                dto.setWinnerTeamId(teamB.getId());
                dto.setWinnerTeamName(teamB.getName());
            } else {
                resolveByPenalties(dto, tieGames, teamA, teamB);
            }
        }

        return dto;
    }

    private void resolveByPenalties(TieDTO dto, List<GameModel> tieGames, TeamModel teamA, TeamModel teamB) {
        GameModel decisiveGame = tieGames.get(tieGames.size() - 1);

        if (decisiveGame.getHomePenalties() == null || decisiveGame.getAwayPenalties() == null) {
            return;
        }

        int penaltiesA, penaltiesB;

        if (decisiveGame.getHomeTeam().getId().equals(teamA.getId())) {
            penaltiesA = decisiveGame.getHomePenalties();
            penaltiesB = decisiveGame.getAwayPenalties();
        } else {
            penaltiesA = decisiveGame.getAwayPenalties();
            penaltiesB = decisiveGame.getHomePenalties();
        }

        dto.setHomePenalties(penaltiesA);
        dto.setAwayPenalties(penaltiesB);

        if (penaltiesA > penaltiesB) {
            dto.setWinnerTeamId(teamA.getId());
            dto.setWinnerTeamName(teamA.getName());
        } else if (penaltiesB > penaltiesA) {
            dto.setWinnerTeamId(teamB.getId());
            dto.setWinnerTeamName(teamB.getName());
        }
    }
}