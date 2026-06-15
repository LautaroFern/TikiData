package com.TikiData.platform.Team.Repository;

import com.TikiData.platform.Player.DTO.PlayerRequestDTO;
import com.TikiData.platform.Team.Model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamModel, Long> {
    TeamModel findByName(String name);

    @Query("SELECT t FROM TeamModel t WHERE " +
            "(:name IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:country IS NULL OR LOWER(t.country) LIKE LOWER(CONCAT('%', :country, '%')))")
    List<TeamModel> searchTeamsByFilters(@Param("name") String name, @Param("country") String country);
}
