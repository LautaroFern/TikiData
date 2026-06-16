package com.TikiData.platform.Standing.Service;

import com.TikiData.platform.Championship.Model.ChampionshipModel;
import com.TikiData.platform.Championship.Repository.ChampionshipRepository;
import com.TikiData.platform.Common.Exception.ResourceNotFoundException;
import com.TikiData.platform.Game.Model.GameModel;
import com.TikiData.platform.Game.Model.GameStatus;
import com.TikiData.platform.Game.Repository.GameRepository;
import com.TikiData.platform.Standing.DTO.StandingDTO;
import com.TikiData.platform.Team.Model.TeamModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StandingService implements IStandingService {

    private final ChampionshipRepository championshipRepository;
    private final GameRepository gameRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StandingDTO> getStandingsByChampionship(Long championshipId) {

        ChampionshipModel championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado con el ID: " + championshipId));

        Map<Long, StandingDTO> standingsMap = new HashMap<>();

        for (TeamModel team : championship.getTeams()) {
            StandingDTO standing = new StandingDTO();
            standing.setTeamId(team.getId());
            standing.setTeamName(team.getName());
            standing.setPlayed(0);
            standing.setWins(0);
            standing.setDraws(0);
            standing.setLosses(0);
            standing.setGoalsFor(0);
            standing.setGoalsAgainst(0);
            standing.setGoalDifference(0);
            standing.setPoints(0);
            standingsMap.put(team.getId(), standing);
        }

        List<GameModel> games = gameRepository.findByChampionshipId(championshipId);

        for (GameModel game : games) {
            if (game.getStatus() != GameStatus.FINISHED) {
                continue;
            }
            if (game.getHomeGoals() == null || game.getAwayGoals() == null) {
                continue;
            }

            Long homeTeamId = game.getHomeTeam().getId();
            Long awayTeamId = game.getAwayTeam().getId();

            StandingDTO homeStanding = standingsMap.get(homeTeamId);
            StandingDTO awayStanding = standingsMap.get(awayTeamId);

            if (homeStanding == null || awayStanding == null) {
                continue;
            }

            int homeGoals = game.getHomeGoals();
            int awayGoals = game.getAwayGoals();

            homeStanding.setPlayed(homeStanding.getPlayed() + 1);
            awayStanding.setPlayed(awayStanding.getPlayed() + 1);

            homeStanding.setGoalsFor(homeStanding.getGoalsFor() + homeGoals);
            homeStanding.setGoalsAgainst(homeStanding.getGoalsAgainst() + awayGoals);

            awayStanding.setGoalsFor(awayStanding.getGoalsFor() + awayGoals);
            awayStanding.setGoalsAgainst(awayStanding.getGoalsAgainst() + homeGoals);

            if (homeGoals > awayGoals) {
                homeStanding.setWins(homeStanding.getWins() + 1);
                homeStanding.setPoints(homeStanding.getPoints() + 3);
                awayStanding.setLosses(awayStanding.getLosses() + 1);
            } else if (homeGoals < awayGoals) {
                awayStanding.setWins(awayStanding.getWins() + 1);
                awayStanding.setPoints(awayStanding.getPoints() + 3);
                homeStanding.setLosses(homeStanding.getLosses() + 1);
            } else {
                homeStanding.setDraws(homeStanding.getDraws() + 1);
                homeStanding.setPoints(homeStanding.getPoints() + 1);
                awayStanding.setDraws(awayStanding.getDraws() + 1);
                awayStanding.setPoints(awayStanding.getPoints() + 1);
            }
        }

        for (StandingDTO standing : standingsMap.values()) {
            standing.setGoalDifference(standing.getGoalsFor() - standing.getGoalsAgainst());
        }

        List<StandingDTO> standings = new ArrayList<>(standingsMap.values());
        standings.sort(
                Comparator.comparing(StandingDTO::getPoints).reversed()
                        .thenComparing(Comparator.comparing(StandingDTO::getGoalDifference).reversed())
                        .thenComparing(Comparator.comparing(StandingDTO::getGoalsFor).reversed())
        );

        return standings;
    }
}
