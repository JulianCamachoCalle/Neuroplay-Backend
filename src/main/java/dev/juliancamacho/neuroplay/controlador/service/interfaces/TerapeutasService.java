package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.TerapeutasDto;

import java.util.List;

public interface TerapeutasService
{
    // CREATE
    TerapeutasDto createTerapeutas(TerapeutasDto terapeutasDto);

    // SELECT BY ID
    TerapeutasDto getTerapeutas(Integer id);

    // SELECT ALL
    List<TerapeutasDto> getAllTerapeutas();

    // UPDATE
    TerapeutasDto updateTerapeutas(Integer id, TerapeutasDto terapeutasDto);

    // DELETE BY ID
    void deleteTerapeutasById(Integer id);
}
