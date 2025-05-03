package dev.juliancamacho.neuroplay.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "terapeutas")
public class Terapeutas {

    @Id
    @Column(name = "usuario_id")
    private Integer usuarioId;

    private String especialidad;

    private String licencia;

    private String biografia;

    @MapsId
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "terapeuta")
    private List<Pacientes> pacientes;
}
