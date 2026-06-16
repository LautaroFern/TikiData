package com.TikiData.platform.Standing.DTO;

import lombok.Data;

@Data
public class StandingDTO {
    private Long teamId;
    private String teamName;
    private Integer played;
    private Integer wins;
    private Integer draws;
    private Integer losses;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalDifference;
    private Integer points;
}