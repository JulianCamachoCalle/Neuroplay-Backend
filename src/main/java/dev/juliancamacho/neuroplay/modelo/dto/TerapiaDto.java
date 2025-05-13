package dev.juliancamacho.neuroplay.modelo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TerapiaDto {

    private Integer id;
    private Integer pacienteId;
    private String pacienteNombre;
    private Integer terapeutaId;
    private String terapeutaNombre;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private List<EjercicioResumenDto> ejercicios;
}
