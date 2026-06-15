package com.TikiData.platform.Team.Repository;

import com.TikiData.platform.Player.DTO.PlayerRequestDTO;
import com.TikiData.platform.Team.DTO.TeamResponseDTO;
import com.TikiData.platform.Team.Model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamModel, Long> {
    Optional<TeamModel> findByName(String name);

    @Query("SELECT t FROM TeamModel t WHERE " +
            "(:name IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:country IS NULL OR LOWER(t.country) LIKE LOWER(CONCAT('%', :country, '%')))")
    List<TeamModel> searchTeamsByFilters(@Param("name") String name, @Param("country") String country);
}
