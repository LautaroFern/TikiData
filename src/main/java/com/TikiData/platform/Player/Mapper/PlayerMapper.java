package com.TikiData.platform.Player.Mapper;

import com.TikiData.platform.Player.DTO.PlayerRequestDTO;
import com.TikiData.platform.Player.DTO.PlayerResponseDTO;
import com.TikiData.platform.Player.Model.PlayerModel;
import com.TikiData.platform.Team.Model.TeamModel;
import org.springframework.stereotype.Component;

@Component

public class PlayerMapper {

    public PlayerModel toEntity(PlayerRequestDTO playerRequestDTO) {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName(playerRequestDTO.getName());
        playerModel.setNumber(playerRequestDTO.getNumber());
        playerModel.setBornDate(playerRequestDTO.getBornDate());
        playerModel.setPosition(playerRequestDTO.getPosition());


        if (playerRequestDTO.getTeamId() != null) {
            TeamModel team = new TeamModel();
            team.setId(playerRequestDTO.getTeamId());
            playerModel.setTeam(team);
        }

        // SOLUCIÓN AL ERROR 2: Devolvemos el objeto
        return playerModel;
    }

    public PlayerResponseDTO toDTO(PlayerModel playerModel) {
        PlayerResponseDTO dto = new PlayerResponseDTO();
        dto.setId(playerModel.getId());
        dto.setName(playerModel.getName());
        dto.setNumber(playerModel.getNumber());
        dto.setBornDate(playerModel.getBornDate());
        dto.setPosition(playerModel.getPosition());

        if (playerModel.getTeam() != null) {
            dto.setTeamId(playerModel.getTeam().getId());
            dto.setTeamName(playerModel.getTeam().getName());
        }
        return dto;
    }
}
