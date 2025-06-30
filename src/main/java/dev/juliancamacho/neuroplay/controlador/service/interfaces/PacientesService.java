package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;

import java.math.BigDecimal;
import java.util.List;

public interface PacientesService {

    // CREATE
    PacientesDto createPacientes(PacientesDto pacientesDto);

    // SELECT BY ID
    PacientesDto getPacientes(Integer id);

    // SELECT BY USUARIO ID
    PacientesDto getPacienteByUsuarioId(Integer usuarioId);

    // SELECT ALL
    List<PacientesDto> getAllPacientes();

    // SELECT PACIENTES BY TERAPEUTA
    List<PacientesDto> getPacientesByTerapeuta(Integer terapeutaId);

    // UPDATE
    PacientesDto updatePacientes(Integer id, PacientesDto pacientesDto);

    // DELETE BY ID
    void deletePacientesById(Integer id);

    // UPDATE PROGRESO PACIENTE
    PacientesDto updateProgresoPaciente(Integer id, BigDecimal progresoTotal,
                                        Integer ejerciciosCompletados,
                                        Integer diasConsecutivos);
}
