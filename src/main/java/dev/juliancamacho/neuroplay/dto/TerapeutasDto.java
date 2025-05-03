package dev.juliancamacho.neuroplay.dto;

import lombok.Data;

@Data
public class TerapeutasDto {

    private Integer usuarioId;
    private String especialidad;
    private String licencia;
    private String biografia;
}
