package dev.juliancamacho.neuroplay.repository;

import dev.juliancamacho.neuroplay.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByUsername(String username);

    boolean existsByTelefono(String telefono);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByTelefono(String telefono);
}
