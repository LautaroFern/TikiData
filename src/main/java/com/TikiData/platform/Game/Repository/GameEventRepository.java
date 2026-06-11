package com.TikiData.platform.Game.Repository;

import com.TikiData.platform.Game.Model.GameEventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameEventRepository extends JpaRepository<GameEventModel, Long> {
}
