package dev.juliancamacho.neuroplay.dto;

import dev.juliancamacho.neuroplay.enums.EstadoUsuario;
import dev.juliancamacho.neuroplay.enums.Genero;
import dev.juliancamacho.neuroplay.enums.Role;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UsuarioDto {

    private Integer id;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private LocalDate fecha_nacimiento;
    private Genero genero;
    private String telefono;
    private LocalDateTime fecha_registro;
    private String avatar;
    private EstadoUsuario estado;
    private Role rol;
}
