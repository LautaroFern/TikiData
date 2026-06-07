package com.TikiData.platform.Championship.DTO;

import com.TikiData.platform.Team.Model.TeamModel;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ChampionshipResponseDTO {
    private Long id;
    private String name;
    private String country;
    private Integer cantidadEquipos;
    private List<TeamModel> listTeams;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer season;
}
