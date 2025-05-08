package dev.juliancamacho.neuroplay.controlador.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private LocalDate fecha_nacimiento;
    private dev.juliancamacho.neuroplay.modelo.enums.Genero genero;
    private String telefono;
}
