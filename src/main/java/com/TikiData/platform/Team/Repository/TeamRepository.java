package com.TikiData.platform.Team.Repository;

import com.TikiData.platform.Player.DTO.PlayerRequestDTO;
import com.TikiData.platform.Team.Model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamModel, Long> {
    TeamModel findByTeamName(String name);
}
