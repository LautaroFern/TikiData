package com.TikiData.platform.User.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDTO {
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
