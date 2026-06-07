package com.TikiData.platform.Team.DTO;

import com.TikiData.platform.Championship.Model.Championship;
import com.TikiData.platform.Player.Model.PlayerModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Championship championship;
    private List<PlayerModel> players;
}
