package dev.juliancamacho.neuroplay.entity;

import dev.juliancamacho.neuroplay.enums.EstadoUsuario;
import dev.juliancamacho.neuroplay.enums.Genero;
import dev.juliancamacho.neuroplay.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha_nacimiento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(nullable = false, length = 9)
    private String telefono;

    @CreationTimestamp
    private LocalDateTime fecha_registro;

    @Column(columnDefinition = "varchar(255) default default.png")
    private String avatar;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_terapeuta", referencedColumnName = "id_terapeuta")
    private Terapeutas terapeuta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_paciente", referencedColumnName = "id_paciente")
    private Pacientes paciente;
}
