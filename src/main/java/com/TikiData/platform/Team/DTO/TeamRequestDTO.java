package com.TikiData.platform.Team.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TeamRequestDTO {
    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotNull(message = "La fecha de fundacion es obligatoria")
    @Past(message = "La fecha de fundacion debe estar en pasado")
    private LocalDate foundationName;

    @NotBlank(message = "El pais es obligatorio")
    @Size(min = 2, max = 60, message = "El pais debe tener entre 2 y 60 caracteres")
    private String country;

    @NotBlank(message = "El estadio es obligatorio")
    @Size(min = 2, max = 120, message = "El estadio debe tener entre 2 y 120 caracteres")
    private String stadium;

    @NotBlank(message = "El apodo es obligatorio")
    @Size(min = 2, max = 50, message = "El apodo debe tener entre 2 y 50 caracteres")
    private String nickname;

    @NotBlank(message = "El nombre del presidente es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del presidente debe tener entre 2 y 100 caracteres")
    private String president;

    @NotNull(message = "El ID del campeonato es obligatorio")
    private Long championshipId;
}
