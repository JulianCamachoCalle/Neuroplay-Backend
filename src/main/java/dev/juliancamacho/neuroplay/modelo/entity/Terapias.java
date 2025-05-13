package dev.juliancamacho.neuroplay.modelo.entity;

import dev.juliancamacho.neuroplay.modelo.enums.EstadoTerapia;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "terapias")
@Data
public class Terapias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Pacientes paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terapeuta_id", nullable = false)
    private Terapeutas terapeuta;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoTerapia estado = EstadoTerapia.ACTIVA;
}
