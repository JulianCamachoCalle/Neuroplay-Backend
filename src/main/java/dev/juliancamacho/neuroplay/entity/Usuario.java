package dev.juliancamacho.neuroplay.entity;

import dev.juliancamacho.neuroplay.enums.EstadoUsuario;
import dev.juliancamacho.neuroplay.enums.Genero;
import dev.juliancamacho.neuroplay.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String telefono;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_registro;

    private String avatar;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Terapeutas terapeuta;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pacientes paciente;

    // VALIDACION DE USUARIOS
    public void setTerapeuta(Terapeutas terapeuta) {
        if (terapeuta != null && this.paciente != null) {
            throw new IllegalStateException("Un usuario no puede ser terapeuta y paciente");
        }
        this.terapeuta = terapeuta;
    }

    public void setPaciente(Pacientes paciente) {
        if (paciente != null && this.paciente != null) {
            throw new IllegalStateException("Un usuario no puede ser paciente y terapeuta");
        }
        this.paciente = paciente;
    }
}
