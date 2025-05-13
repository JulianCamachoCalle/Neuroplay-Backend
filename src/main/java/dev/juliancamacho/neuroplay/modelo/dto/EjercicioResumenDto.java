package dev.juliancamacho.neuroplay.modelo.dto;

import lombok.Data;

@Data
public class EjercicioResumenDto {

    private Integer id;
    private String nombre;
    private String tipo;
    private String nivelDificultad;
    private Integer terapiaId;
}
