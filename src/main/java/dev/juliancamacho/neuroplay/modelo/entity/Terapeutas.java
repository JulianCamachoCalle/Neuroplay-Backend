package dev.juliancamacho.neuroplay.modelo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "terapeutas")
public class Terapeutas {

    @Id
    private Integer id;

    @Column(length = 100)
    private String especialidad;

    private String licencia;

    private String biografia;

    // Relación One-to-One con Usuario (dueño de la relación)
    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relación One-to-Many con Paciente (un terapeuta tiene muchos pacientes)
    @OneToMany(mappedBy = "terapeuta", fetch = FetchType.LAZY)
    private List<Pacientes> pacientes;


}
