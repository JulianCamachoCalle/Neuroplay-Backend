package dev.juliancamacho.neuroplay.modelo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProgresoDto {

    private Integer id;
    private Integer pacienteId;
    private String pacienteNombre;
    private Date fecha;
    private BigDecimal fuerza;
    private BigDecimal movilidad;
    private BigDecimal detalle;
    private String notas;
}