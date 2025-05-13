package dev.juliancamacho.neuroplay.controlador.service.impl;

import dev.juliancamacho.neuroplay.controlador.repository.EjercicioRepository;
import dev.juliancamacho.neuroplay.controlador.repository.PacientesRepository;
import dev.juliancamacho.neuroplay.controlador.repository.SesionRepository;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.SesionService;
import dev.juliancamacho.neuroplay.modelo.dto.SesionDto;
import dev.juliancamacho.neuroplay.modelo.entity.Ejercicios;
import dev.juliancamacho.neuroplay.modelo.entity.Pacientes;
import dev.juliancamacho.neuroplay.modelo.entity.Sesion;
import dev.juliancamacho.neuroplay.modelo.mapper.SesionMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SesionServiceImpl implements SesionService {

    private final SesionRepository sesionRepository;
    private final SesionMapper sesionMapper;
    private final EjercicioRepository ejercicioRepository;
    private final PacientesRepository pacienteRepository;

    @Override
    public SesionDto registrarSesion(SesionDto sesionDto) {
        Ejercicios ejercicio = ejercicioRepository.findById(sesionDto.getEjercicioId())
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado"));

        Pacientes paciente = pacienteRepository.findById(sesionDto.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        Sesion sesion = sesionMapper.sesionDtoToSesion(sesionDto);
        sesion.setEjercicio(ejercicio);
        sesion.setPaciente(paciente);

        Sesion savedSesion = sesionRepository.save(sesion);
        return sesionMapper.sesionToSesionDto(savedSesion);
    }

    @Override
    public SesionDto getSesionById(Integer id) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sesión no encontrada"));
        return sesionMapper.sesionToSesionDto(sesion);
    }

    @Override
    public List<SesionDto> getSesionesByPaciente(Integer pacienteId) {
        return sesionRepository.findByPacienteId(pacienteId).stream()
                .map(sesionMapper::sesionToSesionDto)
                .collect(Collectors.toList());
    }

    @Override
    public SesionDto actualizarSesion(Integer id, SesionDto sesionDto) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sesión no encontrada"));

        // Actualización manual de campos
        sesion.setFecha(sesionDto.getFecha());
        sesion.setDuracionMinutos(sesionDto.getDuracionMinutos());
        sesion.setCompletado(sesionDto.getCompletado());
        sesion.setObservaciones(sesionDto.getObservaciones());

        // Actualizar relaciones solo si los IDs son diferentes
        if (!sesion.getEjercicio().getId().equals(sesionDto.getEjercicioId())) {
            Ejercicios nuevoEjercicio = ejercicioRepository.findById(sesionDto.getEjercicioId())
                    .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado"));
            sesion.setEjercicio(nuevoEjercicio);
        }

        Sesion updatedSesion = sesionRepository.save(sesion);
        return sesionMapper.sesionToSesionDto(updatedSesion);
    }

    @Override
    public void eliminarSesion(Integer id) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sesión no encontrada"));
        sesionRepository.delete(sesion);
    }

    @Override
    public void marcarComoCompletada(Integer sesionId, boolean completada) {
        Sesion sesion = sesionRepository.findById(sesionId)
                .orElseThrow(() -> new EntityNotFoundException("Sesión no encontrada"));
        sesion.setCompletado(completada);
        sesionRepository.save(sesion);
    }
}