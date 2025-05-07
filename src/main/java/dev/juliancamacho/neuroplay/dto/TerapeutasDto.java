package dev.juliancamacho.neuroplay.dto;

import lombok.Data;

@Data
public class TerapeutasDto {

    private Integer idTerapeuta;
    private String especialidad;
    private String licencia;
    private String biografia;
}
