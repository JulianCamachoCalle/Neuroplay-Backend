package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.EjerciciosDto;

import java.util.List;

public interface EjercicioService {
    // CRUD Básico
    EjerciciosDto createEjercicio(EjerciciosDto ejercicioDto);
    List<EjerciciosDto> getAllEjercicios();
    EjerciciosDto getEjercicioById(Integer id);
    List<EjerciciosDto> getEjerciciosByTerapia(Integer terapiaId);
    EjerciciosDto updateEjercicio(Integer id, EjerciciosDto ejercicioDto);
    void deleteEjercicio(Integer id);

    // Filtros
    List<EjerciciosDto> filterEjerciciosByTipo(String tipo);
}