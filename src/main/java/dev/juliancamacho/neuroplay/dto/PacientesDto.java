package dev.juliancamacho.neuroplay.dto;

import dev.juliancamacho.neuroplay.enums.TipoAcv;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PacientesDto {

    private Integer idPaciente;
    private LocalDate fechaAcv;
    private TipoAcv tipoAcv;
    private String antecedentes;
    private String medicacionActual;
    private BigDecimal progresoTotal;
    private Integer ejerciciosCompletados;
    private Integer diasConsecutivos;
}
