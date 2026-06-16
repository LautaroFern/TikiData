package com.TikiData.platform.MiniGame.Service;

import com.TikiData.platform.MiniGame.DTO.MiniGameRequestDTO;
import com.TikiData.platform.MiniGame.DTO.MiniGameResponseDTO;

import java.util.List;

public interface IMiniGameService {
    MiniGameResponseDTO saveMiniGame(MiniGameRequestDTO dto);
    List<MiniGameResponseDTO> findAllMiniGames();
    MiniGameResponseDTO findMiniGameById(Long id);
    MiniGameResponseDTO updateMiniGame(Long id, MiniGameRequestDTO dto);
    void deleteMiniGame(Long id);
}