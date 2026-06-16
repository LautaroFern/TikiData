package com.TikiData.platform.Standing.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TieDTO {
    private String tieId;
    private String round;

    private Long homeTeamId;
    private String homeTeamName;
    private Long awayTeamId;
    private String awayTeamName;

    private Integer homeAggregateGoals;
    private Integer awayAggregateGoals;

    private Integer homePenalties;
    private Integer awayPenalties;

    private Long winnerTeamId;
    private String winnerTeamName;

    private boolean finished;

    private List<Long> gameIds;
}