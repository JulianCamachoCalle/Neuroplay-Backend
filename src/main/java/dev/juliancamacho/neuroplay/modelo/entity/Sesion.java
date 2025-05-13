package dev.juliancamacho.neuroplay.modelo.entity;

import dev.juliancamacho.neuroplay.modelo.enums.Rendimiento;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sesiones")
@Data
public class Sesion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicios ejercicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Pacientes paciente;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fecha;

    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos;

    @Enumerated(EnumType.STRING)
    private Rendimiento rendimiento;

    private Boolean completado = false;

    @Column(name = "activacion_muscular", precision = 5, scale = 2)
    private BigDecimal activacionMuscular;

    @Column(name = "pico_activacion", precision = 5, scale = 2)
    private BigDecimal picoActivacion;

    private Integer repeticiones;

    private String observaciones;
}
