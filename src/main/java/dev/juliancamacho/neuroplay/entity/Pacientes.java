package dev.juliancamacho.neuroplay.entity;

import dev.juliancamacho.neuroplay.enums.TipoAcv;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "pacientes")
public class Pacientes
{
    @Id
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "fecha_acv")
    private Date fechaAcv;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_acv")
    private TipoAcv tipoAcv;

    private String antecedentes;

    @Column(name = "medicacion_actual")
    private String medicacionActual;

    @Column(name = "progreso_actual")
    private Double progresoActual;

    @Column(name = "ejercicios_completados")
    private Integer ejerciciosCompletados;

    @Column(name = "dias_consecutivos")
    private Integer diasConsecutivos;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terapeuta_id", referencedColumnName = "usuario_id", nullable = false)
    private Terapeutas terapeuta;
}
