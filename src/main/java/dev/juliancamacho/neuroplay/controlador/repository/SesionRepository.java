package dev.juliancamacho.neuroplay.controlador.repository;

import dev.juliancamacho.neuroplay.modelo.entity.Sesion;
import dev.juliancamacho.neuroplay.modelo.enums.TipoEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Integer> {

    // Búsquedas básicas
    List<Sesion> findByPacienteId(Integer pacienteId);
    List<Sesion> findByEjercicioId(Integer ejercicioId);
    List<Sesion> findByPacienteIdAndCompletado(Integer pacienteId, Boolean completado);

    // Consultas personalizadas
    @Query("SELECT s FROM Sesion s WHERE s.paciente.id = :pacienteId AND s.ejercicio.tipo = :tipo")
    List<Sesion> findByPacienteAndTipoEjercicio(@Param("pacienteId") Integer pacienteId,
                                                @Param("tipo") TipoEjercicio tipo);

    @Query("SELECT s FROM Sesion s WHERE s.paciente.id = :pacienteId AND DATE(s.fecha) = :fecha")
    List<Sesion> findByPacienteAndFecha(@Param("pacienteId") Integer pacienteId,
                                        @Param("fecha") Date fecha);

    // Consultas para estadísticas
    @Query("SELECT AVG(s.duracionMinutos) FROM Sesion s WHERE s.paciente.id = :pacienteId")
    Optional<Double> findAverageDurationByPaciente(@Param("pacienteId") Integer pacienteId);

    // Consulta nativa para reportes
    @Query(value = """
        SELECT DATE(fecha) as dia, COUNT(*) as total 
        FROM sesiones 
        WHERE paciente_id = :pacienteId 
        GROUP BY DATE(fecha) 
        ORDER BY dia""",
            nativeQuery = true)
    List<Object[]> findSessionsCountByDay(@Param("pacienteId") Integer pacienteId);
}
