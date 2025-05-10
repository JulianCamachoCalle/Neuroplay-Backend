package dev.juliancamacho.neuroplay.controlador.auth.password;

import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Integer idFp;

    @Column(nullable = false)
    private Integer otp;

    @Column(nullable = false, name = "tiempo_expiracion")
    private Date tiempoExpiracion;

    @OneToOne
    private Usuario usuario;
}
