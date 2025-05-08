package dev.juliancamacho.neuroplay.modelo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "terapeutas")
public class Terapeutas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_terapeuta")
    private Integer idTerapeuta;

    @Column(length = 100)
    private String especialidad;

    private String licencia;

    private String biografia;

}
