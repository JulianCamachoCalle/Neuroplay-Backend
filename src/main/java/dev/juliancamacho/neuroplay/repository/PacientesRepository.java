package dev.juliancamacho.neuroplay.repository;

import dev.juliancamacho.neuroplay.entity.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientesRepository extends JpaRepository<Pacientes, Integer> {
}
