package dev.juliancamacho.neuroplay.modelo.dto;

import lombok.Data;

import java.util.List;

@Data
public class TerapeutasDto {

    private Integer id;
    private Integer usuarioId;
    private UsuarioDto usuario; // Datos del usuario
    private String especialidad;
    private String licencia;
    private String biografia;
    private List<PacientesDto> pacientes; // Ids de pacientes
    private Integer cantidadPacientes;
}
