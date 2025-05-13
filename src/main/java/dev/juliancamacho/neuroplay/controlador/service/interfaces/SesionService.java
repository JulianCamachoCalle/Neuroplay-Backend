package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.SesionDto;

import java.util.List;

public interface SesionService {
    // Registro y gestión
    SesionDto registrarSesion(SesionDto sesionDto);
    SesionDto getSesionById(Integer id);
    List<SesionDto> getSesionesByPaciente(Integer pacienteId);
    SesionDto actualizarSesion(Integer id, SesionDto sesionDto);
    void eliminarSesion(Integer id);

    // Progreso
    void marcarComoCompletada(Integer sesionId, boolean completada);
}
