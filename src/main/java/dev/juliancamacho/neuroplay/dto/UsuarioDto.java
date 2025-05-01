package dev.juliancamacho.neuroplay.dto;

import dev.juliancamacho.neuroplay.enums.EstadoUsuario;
import dev.juliancamacho.neuroplay.enums.Genero;
import dev.juliancamacho.neuroplay.enums.TipoUsuario;
import lombok.Data;

import java.util.Date;

@Data
public class UsuarioDto {

    private Integer id;
    private TipoUsuario tipo;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String password;
    private Date fecha_nacimiento;
    private Genero genero;
    private String telefono;
    private Date fecha_registro;
    private String avatar;
    private EstadoUsuario estado;

}
