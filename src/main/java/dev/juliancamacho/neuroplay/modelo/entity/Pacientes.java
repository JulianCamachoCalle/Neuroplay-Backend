package dev.juliancamacho.neuroplay.modelo.entity;

import dev.juliancamacho.neuroplay.modelo.enums.TipoAcv;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "pacientes")
public class Pacientes
{
    @Id
    private Integer id;

    @Column(name = "fecha_acv")
    private Date fechaAcv;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_acv")
    private TipoAcv tipoAcv;

    private String antecedentes;

    @Column(name = "medicacion_actual")
    private String medicacionActual;

    @Column(name = "progreso_total", precision = 5, scale = 2)
    private BigDecimal progresoTotal = BigDecimal.ZERO;

    @Column(name = "ejercicios_completados")
    private Integer ejerciciosCompletados = 0;

    @Column(name = "dias_consecutivos")
    private Integer diasConsecutivos = 0;

    // Relación One-to-One con Usuario (dueño de la relación)
    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relación Many-to-One con Terapeuta (muchos pacientes pueden tener un terapeuta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terapeuta_id", nullable = false)
    private Terapeutas terapeuta;

}
