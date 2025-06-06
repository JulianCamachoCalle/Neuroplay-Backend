package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.ProgresoDto;

import java.util.List;
import java.util.Map;

public interface ProgresoService {
    // Registro y consulta
    ProgresoDto registrarProgreso(ProgresoDto progresoDto);
    List<ProgresoDto> getAllProgresos();
    ProgresoDto getProgresoById(Integer id);
    List<ProgresoDto> getProgresoByPaciente(Integer pacienteId);
    ProgresoDto actualizarProgreso(Integer id, ProgresoDto progresoDto);
    void eliminarProgreso(Integer id);

    // Análisis
    Map<String, Object> getEstadisticasProgreso(Integer pacienteId);
}