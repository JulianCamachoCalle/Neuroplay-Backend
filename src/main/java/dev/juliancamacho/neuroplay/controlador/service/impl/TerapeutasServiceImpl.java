package dev.juliancamacho.neuroplay.controlador.service.impl;

import dev.juliancamacho.neuroplay.controlador.repository.PacientesRepository;
import dev.juliancamacho.neuroplay.controlador.repository.UsuarioRepository;
import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;
import dev.juliancamacho.neuroplay.modelo.dto.TerapeutasDto;
import dev.juliancamacho.neuroplay.modelo.entity.Terapeutas;
import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import dev.juliancamacho.neuroplay.modelo.mapper.PacientesMapper;
import dev.juliancamacho.neuroplay.modelo.mapper.TerapeutasMapper;
import dev.juliancamacho.neuroplay.controlador.repository.TerapeutasRepository;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.TerapeutasService;
import dev.juliancamacho.neuroplay.modelo.mapper.UsuarioMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TerapeutasServiceImpl implements TerapeutasService {

    private final TerapeutasRepository terapeutasRepository;
    private final TerapeutasMapper terapeutasMapper;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PacientesRepository pacientesRepository;
    private final PacientesMapper pacientesMapper;

    // CREATE
    @Override
    public TerapeutasDto createTerapeutas(TerapeutasDto terapeutasDto) {
        // Get ID from either the nested usuario object or direct field
        Integer usuarioId = terapeutasDto.getUsuario() != null ?
                terapeutasDto.getUsuario().getId() :
                terapeutasDto.getUsuarioId();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Terapeutas terapeuta = terapeutasMapper.terapeutasDtoToTerapeutas(terapeutasDto);
        terapeuta.setUsuario(usuario);

        Terapeutas savedTerapeuta = terapeutasRepository.save(terapeuta);
        return buildCompleteTerapeutaDto(savedTerapeuta);
    }

    // SELECT BY ID
    @Override
    public TerapeutasDto getTerapeutas(Integer id) {
        Terapeutas terapeuta = terapeutasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe un terapeuta con ese ID"));
        return buildCompleteTerapeutaDto(terapeuta);
    }

    // SELECT BY USUARIO ID
    @Override
    public TerapeutasDto getTerapeutaByUsuarioId(Integer usuarioId) {
        Terapeutas terapeuta = terapeutasRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("No existe terapeuta para este usuario"));
        return buildCompleteTerapeutaDto(terapeuta);
    }

    // SELECT ALL
    @Override
    public List<TerapeutasDto> getAllTerapeutas() {
        return terapeutasRepository.findAll().stream()
                .map(this::buildCompleteTerapeutaDto)
                .collect(Collectors.toList());
    }

    // GET PACIENTES BY TERAPEUTA
    @Override
    public List<PacientesDto> getPacientesByTerapeutaId(Integer terapeutaId) {
        // Verificar primero que el terapeuta existe
        terapeutasRepository.findById(terapeutaId)
                .orElseThrow(() -> new EntityNotFoundException("Terapeuta no encontrado con ID: " + terapeutaId));

        // Obtener los pacientes filtrados por terapeuta y mapearlos a DTO
        return pacientesRepository.findByTerapeutaId(terapeutaId).stream()
                .map(pacientesMapper::pacientesToPacientesDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public TerapeutasDto updateTerapeutas(Integer id, TerapeutasDto terapeutasDto) {
        Terapeutas terapeuta = terapeutasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Terapeuta no encontrado"));

        // Validar que la licencia es única si cambió
        if (!terapeuta.getLicencia().equals(terapeutasDto.getLicencia()) &&
                terapeutasRepository.existsByLicencia(terapeutasDto.getLicencia())) {
            throw new IllegalStateException("La licencia profesional ya está registrada");
        }

        terapeuta.setEspecialidad(terapeutasDto.getEspecialidad());
        terapeuta.setLicencia(terapeutasDto.getLicencia());
        terapeuta.setBiografia(terapeutasDto.getBiografia());

        Terapeutas updatedTerapeuta = terapeutasRepository.save(terapeuta);
        return buildCompleteTerapeutaDto(updatedTerapeuta);
    }

    // DELETE BY ID
    @Override
    public void deleteTerapeutasById(Integer id) {
        Terapeutas terapeuta = terapeutasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Terapeuta no encontrado"));

        // Verificar si tiene pacientes asignados
        if (!terapeuta.getPacientes().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar, el terapeuta tiene pacientes asignados");
        }

        terapeutasRepository.delete(terapeuta);
    }

    // Métodos auxiliares privados
    private TerapeutasDto buildCompleteTerapeutaDto(Terapeutas terapeuta) {
        TerapeutasDto dto = terapeutasMapper.terapeutasToTerapeutasDto(terapeuta);

        // Handle null pacientes
        if (terapeuta.getPacientes() != null) {
            dto.setPacientes(terapeuta.getPacientes().stream()
                    .map(pacientesMapper::pacientesToPacientesDto)
                    .collect(Collectors.toList()));
        } else {
            dto.setPacientes(Collections.emptyList());
        }

        return dto;
    }

}
