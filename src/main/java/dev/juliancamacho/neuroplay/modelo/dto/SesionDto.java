package dev.juliancamacho.neuroplay.modelo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SesionDto {
    private Integer id;
    private Integer ejercicioId;
    private String ejercicioNombre;
    private Integer pacienteId;
    private String pacienteNombre;
    private Date fecha;
    private Integer duracionMinutos;
    private String rendimiento;
    private Boolean completado;
    private BigDecimal activacionMuscular;
    private BigDecimal picoActivacion;
    private Integer repeticiones;
    private String observaciones;
}
