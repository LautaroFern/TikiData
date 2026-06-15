package com.TikiData.platform.Player.Repository;

import com.TikiData.platform.Player.Model.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Long> {
    PlayerModel findByName(String name);
}
