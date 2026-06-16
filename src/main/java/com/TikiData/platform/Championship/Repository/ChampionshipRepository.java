package com.TikiData.platform.Championship.Repository;

import com.TikiData.platform.Championship.Model.ChampionshipModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampionshipRepository extends JpaRepository<ChampionshipModel, Long> {
    Optional<ChampionshipModel> findByName(String name);
}
