package dev.juliancamacho.neuroplay.modelo.dto;

import lombok.Data;

@Data
public class EjerciciosDto {
    private Integer id;
    private Integer terapiaId;
    private String terapiaNombre;
    private String nombre;
    private String descripcion;
    private String tipo;
    private Integer repeticiones;
    private Integer duracionMinutos;
    private String nivelDificultad;
    private String videoUrl;
    private String imagenUrl;
}
