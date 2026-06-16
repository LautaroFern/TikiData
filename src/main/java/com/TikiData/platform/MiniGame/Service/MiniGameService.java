package com.TikiData.platform.MiniGame.Service;

import com.TikiData.platform.Common.Exception.ResourceNotFoundException;
import com.TikiData.platform.MiniGame.DTO.MiniGameRequestDTO;
import com.TikiData.platform.MiniGame.DTO.MiniGameResponseDTO;
import com.TikiData.platform.MiniGame.Mapper.MiniGameMapper;
import com.TikiData.platform.MiniGame.Model.MiniGameModel;
import com.TikiData.platform.MiniGame.Repository.MiniGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiniGameService implements IMiniGameService {

    private final MiniGameRepository miniGameRepository;
    private final MiniGameMapper miniGameMapper;

    @Override
    @Transactional
    public MiniGameResponseDTO saveMiniGame(MiniGameRequestDTO dto) {
        MiniGameModel miniGameModel = miniGameMapper.toEntity(dto);
        return miniGameMapper.toDTO(miniGameRepository.save(miniGameModel));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MiniGameResponseDTO> findAllMiniGames() {
        return miniGameRepository.findAll()
                .stream()
                .map(miniGameMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MiniGameResponseDTO findMiniGameById(Long id) {
        MiniGameModel miniGameModel = miniGameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Minijuego no encontrado con el ID: " + id));
        return miniGameMapper.toDTO(miniGameModel);
    }

    @Override
    @Transactional
    public MiniGameResponseDTO updateMiniGame(Long id, MiniGameRequestDTO dto) {
        MiniGameModel miniGameModel = miniGameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Minijuego no encontrado con el ID: " + id));

        miniGameModel.setName(dto.getName());
        miniGameModel.setDescription(dto.getDescription());
        miniGameModel.setExternalUrl(dto.getExternalUrl());
        miniGameModel.setImageUrl(dto.getImageUrl());

        return miniGameMapper.toDTO(miniGameRepository.save(miniGameModel));
    }

    @Override
    @Transactional
    public void deleteMiniGame(Long id) {
        MiniGameModel miniGameModel = miniGameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Minijuego no encontrado con el ID: " + id));
        miniGameRepository.delete(miniGameModel);
    }
}