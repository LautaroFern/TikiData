package com.TikiData.platform.Player.Mapper;

import com.TikiData.platform.Player.DTO.PlayerRequestDTO;
import com.TikiData.platform.Player.Model.PlayerModel;

public class PlayerMapper {
    public PlayerModel toEntity(PlayerRequestDTO playerRequestDTO) {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName(playerRequestDTO.getName());
        playerModel.setNumber(playerRequestDTO.getNumber());
        playerModel.setBornDate(playerRequestDTO.getBornDate());
        playerModel.setPosition(playerRequestDTO.getPosition());
        playerModel.setTeam(playerRequestDTO);
    }
}
