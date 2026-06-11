package com.TikiData.platform.Player.Repository;

import com.TikiData.platform.Common.Exception.PlayerNotFoundException;
import com.TikiData.platform.Player.DTO.PlayerResponseDTO;
import com.TikiData.platform.Player.Model.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerModel, Long> {
    PlayerModel findByPlayerName(String name);
}
