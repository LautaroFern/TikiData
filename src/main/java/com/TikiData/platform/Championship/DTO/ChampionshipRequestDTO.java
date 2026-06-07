package com.TikiData.platform.Championship.DTO;

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

    @NotNull(message = "Cantidad de equipos is obligatory")
    private Integer cantidadEquipos;

    @NotNull(message = "The start date is obligatory")
    private LocalDate startDate;

    @NotNull(message = "The end date is obligatory")
    private LocalDate endDate;

    @NotNull(message = "The season is obligatory")
    private Integer season;
}
