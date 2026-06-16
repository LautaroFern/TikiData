package com.TikiData.platform.Game.Repository;

import com.TikiData.platform.Game.Model.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Long> {
    List<GameModel> findByChampionshipId(Long championshipId);
    List<GameModel> findByHomeTeamIdOrAwayTeamId(Long teamId, Long secondTeamId);
}
