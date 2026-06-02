package com.TikiData.platform.Team.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDTO {

    private String name;

    private LocalDate foundationName;

    private String country;

    private String stadium;

    private String nickname;

    private String president;

}
