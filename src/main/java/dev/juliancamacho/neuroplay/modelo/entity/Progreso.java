package dev.juliancamacho.neuroplay.modelo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "progreso")
@Data
public class Progreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Pacientes paciente;

    @Column(nullable = false)
    private Date fecha;

    @Column(precision = 5, scale = 2)
    private BigDecimal fuerza;

    @Column(precision = 5, scale = 2)
    private BigDecimal movilidad;

    @Column(precision = 5, scale = 2)
    private BigDecimal detalle;

    private String notas;
}