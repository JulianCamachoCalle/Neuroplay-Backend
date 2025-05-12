package dev.juliancamacho.neuroplay.modelo.entity;

import dev.juliancamacho.neuroplay.controlador.auth.password.ForgotPassword;
import dev.juliancamacho.neuroplay.modelo.enums.EstadoUsuario;
import dev.juliancamacho.neuroplay.modelo.enums.Genero;
import dev.juliancamacho.neuroplay.modelo.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Role rol;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(nullable = false, length = 9)
    private String telefono;

    @CreationTimestamp
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(length = 255)
    private String avatar;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private ForgotPassword forgotPassword;

    // Relación One-to-One con Terapeuta (si el usuario es terapeuta)
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Terapeutas terapeutaInfo;

    // Relación One-to-One con Paciente (si el usuario es paciente)
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pacientes pacienteInfo;

    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
