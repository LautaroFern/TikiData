package com.TikiData.platform.MiniGame.Mapper;

import com.TikiData.platform.MiniGame.DTO.MiniGameRequestDTO;
import com.TikiData.platform.MiniGame.DTO.MiniGameResponseDTO;
import com.TikiData.platform.MiniGame.Model.MiniGameModel;
import org.springframework.stereotype.Component;

@Component
public class MiniGameMapper {

    public MiniGameModel toEntity(MiniGameRequestDTO dto) {
        MiniGameModel miniGameModel = new MiniGameModel();
        miniGameModel.setName(dto.getName());
        miniGameModel.setDescription(dto.getDescription());
        miniGameModel.setExternalUrl(dto.getExternalUrl());
        miniGameModel.setImageUrl(dto.getImageUrl());
        return miniGameModel;
    }

    public MiniGameResponseDTO toDTO(MiniGameModel miniGameModel) {
        MiniGameResponseDTO dto = new MiniGameResponseDTO();
        dto.setId(miniGameModel.getId());
        dto.setName(miniGameModel.getName());
        dto.setDescription(miniGameModel.getDescription());
        dto.setExternalUrl(miniGameModel.getExternalUrl());
        dto.setImageUrl(miniGameModel.getImageUrl());
        return dto;
    }
}