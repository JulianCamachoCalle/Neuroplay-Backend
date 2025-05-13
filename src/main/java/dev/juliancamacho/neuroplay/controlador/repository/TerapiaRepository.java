package dev.juliancamacho.neuroplay.controlador.repository;

import dev.juliancamacho.neuroplay.modelo.entity.Terapias;
import dev.juliancamacho.neuroplay.modelo.enums.EstadoTerapia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerapiaRepository extends JpaRepository<Terapias, Integer> {

    // Búsquedas básicas
    List<Terapias> findByPacienteId(Integer pacienteId);
    List<Terapias> findByTerapeutaId(Integer terapeutaId);
    List<Terapias> findByEstado(EstadoTerapia estado);

    // Consultas personalizadas
    @Query("SELECT t FROM Terapias t WHERE t.paciente.id = :pacienteId AND t.estado = 'ACTIVA'")
    List<Terapias> findActiveByPaciente(@Param("pacienteId") Integer pacienteId);

    // Consultas para estadísticas
    @Query("SELECT COUNT(t) FROM Terapias t WHERE t.terapeuta.id = :terapeutaId AND t.estado = 'COMPLETADA'")
    long countCompletedByTerapeuta(@Param("terapeutaId") Integer terapeutaId);

    // Verificaciones
    boolean existsByPacienteIdAndEstado(Integer pacienteId, EstadoTerapia estado);
}