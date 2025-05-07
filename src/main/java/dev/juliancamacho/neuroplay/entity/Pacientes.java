package dev.juliancamacho.neuroplay.entity;

import dev.juliancamacho.neuroplay.enums.TipoAcv;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pacientes")
public class Pacientes
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(name = "fecha_acv")
    private LocalDate fechaAcv;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_acv")
    private TipoAcv tipoAcv;

    private String antecedentes;

    @Column(name = "medicacion_actual")
    private String medicacionActual;

    @Column(name = "progreso_total", precision = 5, scale = 2, columnDefinition = "DECIMAL(5,2) DEFAULT 0.00")
    private BigDecimal progresoTotal;

    @Column(name = "ejercicios_completados", columnDefinition = "INT DEFAULT 0")
    private Integer ejerciciosCompletados;

    @Column(name = "dias_consecutivos", columnDefinition = "INT DEFAULT 0")
    private Integer diasConsecutivos;

}
