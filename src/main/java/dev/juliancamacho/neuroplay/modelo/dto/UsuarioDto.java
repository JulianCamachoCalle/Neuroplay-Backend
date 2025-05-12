package dev.juliancamacho.neuroplay.modelo.dto;

import dev.juliancamacho.neuroplay.modelo.enums.EstadoUsuario;
import dev.juliancamacho.neuroplay.modelo.enums.Genero;
import dev.juliancamacho.neuroplay.modelo.enums.Role;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UsuarioDto {

    private Integer id;
    private String nombre;
    private String apellido;
    private String username; // email
    private String password;
    private Date fechaNacimiento;
    private Genero genero;
    private String telefono;
    private LocalDateTime fechaRegistro;
    private String avatar;
    private EstadoUsuario estado;
    private Role rol;

    // EXTRAS
    private String especialidad; // Para terapeutas
    private String licencia;     // Para terapeutas
    private Integer terapeutaId; // Para pacientes
    private Date fechaACV;  // Para pacientes
}
