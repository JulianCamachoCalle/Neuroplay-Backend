package dev.juliancamacho.neuroplay.dto;

import dev.juliancamacho.neuroplay.enums.TipoAcv;
import lombok.Data;

import java.util.Date;

@Data
public class PacientesDto {

    private Integer usuarioId;
    private Date fechaAcv;
    private TipoAcv tipoAcv;
    private String antecedentes;
    private String medicacionActual;
    private Double progresoActual;
    private Integer ejerciciosCompletados;
    private Integer diasConsecutivos;
}
