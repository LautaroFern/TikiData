package com.TikiData.platform.Player.DTO;
import com.TikiData.platform.Player.Model.Position;
import lombok.Data;


import java.time.LocalDate;
import java.time.Period;

@Data
public class PlayerResponseDTO {
    private Long id;
    private String name;
    private Integer number;
    private LocalDate bornDate;
    private Position position;
    private Long teamId;
    private String teamName;

    public Integer getAge() {
        return Period.between(this.bornDate, LocalDate.now()).getYears();
    }
}