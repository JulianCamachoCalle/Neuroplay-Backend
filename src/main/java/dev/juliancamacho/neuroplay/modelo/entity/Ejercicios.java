package dev.juliancamacho.neuroplay.modelo.entity;

import dev.juliancamacho.neuroplay.modelo.enums.NivelDificultad;
import dev.juliancamacho.neuroplay.modelo.enums.TipoEjercicio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ejercicios")
@Data
public class Ejercicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terapia_id", nullable = false)
    private Terapias terapia;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEjercicio tipo; // Enum: FISICO, COGNITIVO, LENGUAJE

    private Integer repeticiones;

    @Column(name = "duracion_minutos")
    private Integer duracionMinutos;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_dificultad")
    private NivelDificultad nivelDificultad = NivelDificultad.MEDIO;

    @Column(name = "video_url", length = 255)
    private String videoUrl;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;
}
