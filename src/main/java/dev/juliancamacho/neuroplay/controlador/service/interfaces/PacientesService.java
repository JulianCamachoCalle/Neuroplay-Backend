package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;

import java.util.List;

public interface PacientesService {

    // CREATE
    PacientesDto createPacientes(PacientesDto pacientesDto);

    // SELECT BY ID
    PacientesDto getPacientes(Integer id);

    // SELECT ALL
    List<PacientesDto> getAllPacientes();

    // UPDATE
    PacientesDto updatePacientes(Integer id, PacientesDto pacientesDto);

    // DELETE BY ID
    void deletePacientesById(Integer id);
}
