package dev.juliancamacho.neuroplay.controlador.repository;

import dev.juliancamacho.neuroplay.modelo.entity.Pacientes;
import dev.juliancamacho.neuroplay.modelo.enums.TipoAcv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacientesRepository extends JpaRepository<Pacientes, Integer> {

    // Buscar paciente por ID de usuario
    Optional<Pacientes> findByUsuarioId(Integer usuarioId);

    // Existe el usuario por Id
    boolean existsByUsuarioId(Integer usuarioId);

    // Listar todos los pacientes de un terapeuta específico
    List<Pacientes> findByTerapeutaId(Integer terapeutaId);

    // Contar pacientes por terapeuta
    long countByTerapeutaId(Integer terapeutaId);

    // Buscar pacientes con progreso mayor a X
    List<Pacientes> findByProgresoTotalGreaterThan(BigDecimal progreso);

    // Buscar pacientes por tipo de ACV
    List<Pacientes> findByTipoAcv(TipoAcv tipoAcv);
}
