package dev.juliancamacho.neuroplay.modelo.dto;

import dev.juliancamacho.neuroplay.modelo.enums.TipoAcv;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PacientesDto {

    private Integer id;
    private UsuarioDto usuario; // Datos del usuario
    private Integer terapeutaId; // Id de terapeuta
    private String terapeutaNombre;
    private Date fechaAcv;
    private TipoAcv tipoAcv;
    private String antecedentes;
    private String medicacionActual;
    private BigDecimal progresoTotal;
    private Integer ejerciciosCompletados;
    private Integer diasConsecutivos;
}
