package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.ProgresoDto;

import java.math.BigDecimal;
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

    // Métodos específicos para terapia
    ProgresoDto registrarProgresoTerapia(Integer pacienteId, BigDecimal detalle, String notas);
    ProgresoDto registrarSesionCompletada(Integer pacienteId);
    List<ProgresoDto> getProgresoByTerapia(Integer pacienteId);

    // Análisis
    Map<String, Object> getEstadisticasProgreso(Integer pacienteId);

}