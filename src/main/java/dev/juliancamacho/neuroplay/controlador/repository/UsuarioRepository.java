package dev.juliancamacho.neuroplay.controlador.repository;

import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import dev.juliancamacho.neuroplay.modelo.enums.EstadoUsuario;
import dev.juliancamacho.neuroplay.modelo.enums.Genero;
import dev.juliancamacho.neuroplay.modelo.enums.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByUsername(String username);
    boolean existsByTelefono(String telefono);
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByTelefono(String telefono);

    // Buscar usuarios por rol
    List<Usuario> findByRol(Role rol);

    // Buscar usuarios por estado
    List<Usuario> findByEstado(EstadoUsuario estado);

    // Buscar usuarios por género
    List<Usuario> findByGenero(Genero genero);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.password = ?2 where u.username = ?1")
    void updatePassword(String username, String password);

    // Buscar usuarios con fecha de registro entre dos fechas
    @Query("SELECT u FROM Usuario u WHERE u.fechaRegistro BETWEEN :inicio AND :fin")
    List<Usuario> findUsuariosRegistradosEntreFechas(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);
}
