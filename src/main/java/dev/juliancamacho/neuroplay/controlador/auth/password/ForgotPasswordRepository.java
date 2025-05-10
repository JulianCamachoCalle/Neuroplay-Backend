package dev.juliancamacho.neuroplay.controlador.auth.password;

import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ForgotPasswordRepository extends CrudRepository<ForgotPassword, Integer> {

    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.usuario = ?2")
    Optional<ForgotPassword> findOtpAndUsuario(Integer otp, Usuario usuario);
}
