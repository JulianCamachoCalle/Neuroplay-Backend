package dev.juliancamacho.neuroplay.controlador.service.impl;

import dev.juliancamacho.neuroplay.controlador.repository.PacientesRepository;
import dev.juliancamacho.neuroplay.controlador.repository.TerapeutasRepository;
import dev.juliancamacho.neuroplay.controlador.repository.TerapiaRepository;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.TerapiaService;
import dev.juliancamacho.neuroplay.modelo.dto.TerapiaDto;
import dev.juliancamacho.neuroplay.modelo.entity.Pacientes;
import dev.juliancamacho.neuroplay.modelo.entity.Terapeutas;
import dev.juliancamacho.neuroplay.modelo.entity.Terapias;
import dev.juliancamacho.neuroplay.modelo.enums.EstadoTerapia;
import dev.juliancamacho.neuroplay.modelo.mapper.TerapiaMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TerapiaServiceImpl implements TerapiaService {

    private final TerapiaRepository terapiaRepository;
    private final TerapiaMapper terapiaMapper;
    private final PacientesRepository pacienteRepository;
    private final TerapeutasRepository terapeutaRepository;

    @Override
    public TerapiaDto createTerapia(TerapiaDto terapiaDto) {
        Pacientes paciente = pacienteRepository.findById(terapiaDto.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        Terapeutas terapeuta = terapeutaRepository.findById(terapiaDto.getTerapeutaId())
                .orElseThrow(() -> new EntityNotFoundException("Terapeuta no encontrado"));

        Terapias terapia = terapiaMapper.terapiaDtoToTerapia(terapiaDto);
        terapia.setPaciente(paciente);
        terapia.setTerapeuta(terapeuta);

        Terapias savedTerapia = terapiaRepository.save(terapia);
        return terapiaMapper.terapiaToTerapiaDto(savedTerapia);
    }

    @Override
    public TerapiaDto getTerapiaById(Integer id) {
        Terapias terapia = terapiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Terapia no encontrada"));
        return terapiaMapper.terapiaToTerapiaDto(terapia);
    }

    @Override
    public List<TerapiaDto> getAllTerapias() {
        return terapiaRepository.findAll().stream()
                .map(terapiaMapper::terapiaToTerapiaDto)
                .collect(Collectors.toList());
    }

    @Override
    public TerapiaDto updateTerapia(Integer id, TerapiaDto terapiaDto) {
        Terapias terapia = terapiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Terapia no encontrada"));

        // Actualización manual de campos básicos
        terapia.setNombre(terapiaDto.getNombre());
        terapia.setDescripcion(terapiaDto.getDescripcion());
        terapia.setFechaInicio(terapiaDto.getFechaInicio());
        terapia.setFechaFin(terapiaDto.getFechaFin());

        // Actualización de relaciones con validación
        if (!terapia.getPaciente().getId().equals(terapiaDto.getPacienteId())) {
            Pacientes nuevoPaciente = pacienteRepository.findById(terapiaDto.getPacienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
            terapia.setPaciente(nuevoPaciente);
        }

        if (!terapia.getTerapeuta().getId().equals(terapiaDto.getTerapeutaId())) {
            Terapeutas nuevoTerapeuta = terapeutaRepository.findById(terapiaDto.getTerapeutaId())
                    .orElseThrow(() -> new EntityNotFoundException("Terapeuta no encontrado"));
            terapia.setTerapeuta(nuevoTerapeuta);
        }

        Terapias updatedTerapia = terapiaRepository.save(terapia);
        return terapiaMapper.terapiaToTerapiaDto(updatedTerapia);
    }

    @Override
    public void deleteTerapia(Integer id) {
        Terapias terapia = terapiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Terapia no encontrada"));
        terapiaRepository.delete(terapia);
    }

    @Override
    public List<TerapiaDto> getTerapiasByPaciente(Integer pacienteId) {
        return terapiaRepository.findByPacienteId(pacienteId).stream()
                .map(terapiaMapper::terapiaToTerapiaDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TerapiaDto> getTerapiasByTerapeuta(Integer terapeutaId) {
        return terapiaRepository.findByTerapeutaId(terapeutaId).stream()
                .map(terapiaMapper::terapiaToTerapiaDto)
                .collect(Collectors.toList());
    }

    @Override
    public void cambiarEstadoTerapia(Integer id, String estado) {
        Terapias terapia = terapiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Terapia no encontrada"));

        try {
            EstadoTerapia nuevoEstado = EstadoTerapia.valueOf(estado.toUpperCase());
            terapia.setEstado(nuevoEstado);
            terapiaRepository.save(terapia);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado no válido: " + estado);
        }
    }
}
