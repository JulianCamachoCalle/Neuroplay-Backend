package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.TerapiaDto;

import java.util.List;

public interface TerapiaService {
    // CRUD Básico
    TerapiaDto createTerapia(TerapiaDto terapiaDto);
    TerapiaDto getTerapiaById(Integer id);
    List<TerapiaDto> getAllTerapias();
    TerapiaDto updateTerapia(Integer id, TerapiaDto terapiaDto);
    void deleteTerapia(Integer id);

    // Consultas específicas
    List<TerapiaDto> getTerapiasByPaciente(Integer pacienteId);
    List<TerapiaDto> getTerapiasByTerapeuta(Integer terapeutaId);
    void cambiarEstadoTerapia(Integer id, String estado);
}
