package com.TikiData.platform.Player.DTO;

import com.TikiData.platform.Player.Model.Position;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PlayerRequestDTO {

    @NotBlank(message = "El nombre del jugador es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotNull(message = "El número de camiseta es obligatorio")
    @Min(value = 1, message = "El número de camiseta debe ser mayor a 0")
    @Max(value = 99, message = "El número de camiseta no puede ser mayor a 99")
    private Integer number;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate bornDate;

    @NotNull(message = "La posicion es obligatoria")
    private Position position;

    @Positive(message = "El ID del equipo debe ser un numero positivo")
    private Long teamId;
}
