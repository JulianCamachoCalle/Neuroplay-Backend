package dev.juliancamacho.neuroplay.controlador.auth;

import dev.juliancamacho.neuroplay.modelo.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private Date fechaNacimiento;
    private Genero genero;
    private String telefono;
}
