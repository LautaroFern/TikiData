package com.TikiData.platform.Game.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseDTO {
    private Long id;
    private LocalDateTime gameDate;
    private String stadium;
    private String status;
    private Integer homeGoals;
    private Integer awayGoals;
    private Long championshipId;
    private String championshipName;
    private Long homeTeamId;
    private String homeTeamName;
    private Long awayTeamId;
    private String awayTeamName;
    private List<GameEventResponseDTO> events;
}
