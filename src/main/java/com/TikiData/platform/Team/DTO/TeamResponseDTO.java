package com.TikiData.platform.Team.DTO;

import com.TikiData.platform.Player.Model.PlayerModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class TeamResponseDTO {
    private Long id;
    private String name;
    private LocalDate foundationDate;
    private String country;
    private String stadium;
    private String nickname;
    private String president;
    //Hay que armar el championship para vincularlo
    //private ChampionshipModel championship;
    private List<PlayerModel> players;
}
