package dev.juliancamacho.neuroplay.controlador.repository;

import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByUsername(String username);

    boolean existsByTelefono(String telefono);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByTelefono(String telefono);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.password = ?2 where u.username = ?1")
    void updatePassword(String username, String password);
}
