package com.TikiData.platform.MiniGame.Repository;

import com.TikiData.platform.MiniGame.Model.MiniGameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiniGameRepository extends JpaRepository<MiniGameModel, Long> {
}