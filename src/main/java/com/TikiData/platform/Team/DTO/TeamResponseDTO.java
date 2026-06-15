package com.TikiData.platform.Team.DTO;

import com.TikiData.platform.Player.DTO.PlayerResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TeamResponseDTO {
    private Long id;
    private String name;
    private LocalDate foundationDate;
    private String country;
    private String stadium;
    private String nickname;
    private String president;
    private Long championshipId;
    private String championshipName;
    private List<PlayerResponseDTO> players;
}
