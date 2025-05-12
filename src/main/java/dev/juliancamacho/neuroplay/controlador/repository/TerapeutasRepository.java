package dev.juliancamacho.neuroplay.controlador.repository;

import dev.juliancamacho.neuroplay.modelo.entity.Terapeutas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerapeutasRepository extends JpaRepository<Terapeutas, Integer> {

    // Buscar terapeuta por ID de usuario
    Optional<Terapeutas> findByUsuarioId(Integer usuarioId);

    // Buscar terapeuta por número de licencia
    Optional<Terapeutas> findByLicencia(String licencia);

    // Buscar si exsite por licencia
    boolean existsByLicencia(String licencia);

    // Buscar si existe por usuario id
    boolean existsByUsuarioId(Integer usuarioId);

    // Buscar terapeutas por especialidad
    List<Terapeutas> findByEspecialidadContainingIgnoreCase(String especialidad);

    // Buscar terapeutas con pacientes asignados
    @Query("SELECT t FROM Terapeutas t WHERE SIZE(t.pacientes) > 0")
    List<Terapeutas> findTerapeutasConPacientes();
}
