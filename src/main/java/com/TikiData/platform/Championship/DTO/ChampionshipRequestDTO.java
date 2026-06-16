package com.TikiData.platform.Championship.DTO;

import com.TikiData.platform.Championship.Model.TournamentFormat;
import com.TikiData.platform.Common.Validation.EvenNumber;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ChampionshipRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotBlank(message = "El país es obligatorio")
    @Size(min = 2, max = 100, message = "El país debe tener entre 2 y 100 caracteres")
    private String country;

    @NotNull(message = "El formato del torneo es obligatorio")
    private TournamentFormat format;

    @NotNull(message = "La cantidad de equipos es obligatoria")
    @Min(value = 2, message = "Debe haber al menos 2 equipos")
    @EvenNumber(message = "La cantidad de equipos debe ser un número par")
    private Integer numberOfTeams;

    @NotNull(message = "La fecha de inicio es obligatorio")
    private LocalDate startDate;

    @NotNull(message = "La fecha de finalizacion es obligatoria")
    private LocalDate endDate;

    @NotNull(message = "La temporada es obligatoria")
    private Integer season;
}
