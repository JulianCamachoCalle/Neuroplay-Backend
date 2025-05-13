package dev.juliancamacho.neuroplay.controlador.repository;

import dev.juliancamacho.neuroplay.modelo.entity.Progreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgresoRepository extends JpaRepository<Progreso, Integer> {

    // Búsquedas básicas
    List<Progreso> findByPacienteId(Integer pacienteId);
    List<Progreso> findByPacienteIdOrderByFechaDesc(Integer pacienteId);

    // Consultas personalizadas
    @Query("SELECT p FROM Progreso p WHERE p.paciente.id = :pacienteId AND p.fecha BETWEEN :start AND :end")
    List<Progreso> findByPacienteAndDateRange(@Param("pacienteId") Integer pacienteId,
                                              @Param("start") Date start,
                                              @Param("end") Date end);

    @Query("SELECT p FROM Progreso p WHERE p.paciente.id = :pacienteId ORDER BY p.fecha DESC LIMIT 1")
    Optional<Progreso> findLatestByPaciente(@Param("pacienteId") Integer pacienteId);

    // Consultas para estadísticas
    @Query("""
        SELECT 
            AVG(p.fuerza) as avgFuerza,
            AVG(p.movilidad) as avgMovilidad,
            AVG(p.detalle) as avgDetalle
        FROM Progreso p 
        WHERE p.paciente.id = :pacienteId""")
    ProgresoStatsProjection getAverageStats(@Param("pacienteId") Integer pacienteId);

    // Proyección para datos específicos
    @Query("SELECT p.fecha as fecha, p.fuerza as fuerza FROM Progreso p WHERE p.paciente.id = :pacienteId")
    List<ProgresoChartProjection> findForChart(@Param("pacienteId") Integer pacienteId);

    public interface ProgresoStatsProjection {
        Double getAvgFuerza();
        Double getAvgMovilidad();
        Double getAvgDetalle();
    }

    public interface ProgresoChartProjection {
        Date getFecha();
        BigDecimal getFuerza();
    }
}
