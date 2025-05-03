package dev.juliancamacho.neuroplay.repository;

import dev.juliancamacho.neuroplay.entity.Terapeutas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerapeutasRepository extends JpaRepository<Terapeutas, Integer> {
}
