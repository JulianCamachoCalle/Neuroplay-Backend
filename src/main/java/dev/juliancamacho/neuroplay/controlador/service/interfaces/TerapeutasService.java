package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;
import dev.juliancamacho.neuroplay.modelo.dto.TerapeutasDto;

import java.util.List;

public interface TerapeutasService
{
    // CREATE
    TerapeutasDto createTerapeutas(TerapeutasDto terapeutasDto);

    // SELECT BY ID
    TerapeutasDto getTerapeutas(Integer id);

    // SELECT BY USUARIO ID
    TerapeutasDto getTerapeutaByUsuarioId(Integer usuarioId);

    // SELECT ALL
    List<TerapeutasDto> getAllTerapeutas();

    List<PacientesDto> getPacientesByTerapeutaId(Integer id);

    // UPDATE
    TerapeutasDto updateTerapeutas(Integer id, TerapeutasDto terapeutasDto);

    // DELETE BY ID
    void deleteTerapeutasById(Integer id);
}
