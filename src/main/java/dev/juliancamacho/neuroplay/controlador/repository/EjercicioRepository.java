package dev.juliancamacho.neuroplay.controlador.repository;

import dev.juliancamacho.neuroplay.modelo.entity.Ejercicios;
import dev.juliancamacho.neuroplay.modelo.enums.TipoEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicios, Integer> {

    // Búsquedas básicas
    List<Ejercicios> findByTerapiaId(Integer terapiaId);
    List<Ejercicios> findByTerapiaIdAndTipo(Integer terapiaId, TipoEjercicio tipo);
    List<Ejercicios> findByTipo(TipoEjercicio tipo);

    // Consultas personalizadas
    @Query("SELECT e FROM Ejercicios e JOIN FETCH e.terapia WHERE e.id = :id")
    Optional<Ejercicios> findByIdWithTerapia(@Param("id") Integer id);

    @Query("SELECT e FROM Ejercicios e WHERE e.terapia.paciente.id = :pacienteId")
    List<Ejercicios> findByPacienteId(@Param("pacienteId") Integer pacienteId);
}
