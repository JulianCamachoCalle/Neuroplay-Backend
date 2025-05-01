package dev.juliancamacho.neuroplay.entity;

import dev.juliancamacho.neuroplay.enums.EstadoUsuario;
import dev.juliancamacho.neuroplay.enums.Genero;
import dev.juliancamacho.neuroplay.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

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

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(length = 20)
    private String telefono;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_registro;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'default.png'")
    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo') DEFAULT 'activo'")
    private EstadoUsuario estado;

}
