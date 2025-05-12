package dev.juliancamacho.neuroplay.controlador.service.impl;

import dev.juliancamacho.neuroplay.controlador.repository.TerapeutasRepository;
import dev.juliancamacho.neuroplay.controlador.repository.UsuarioRepository;
import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;
import dev.juliancamacho.neuroplay.modelo.entity.Pacientes;
import dev.juliancamacho.neuroplay.modelo.entity.Terapeutas;
import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import dev.juliancamacho.neuroplay.modelo.mapper.PacientesMapper;
import dev.juliancamacho.neuroplay.controlador.repository.PacientesRepository;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.PacientesService;
import dev.juliancamacho.neuroplay.modelo.mapper.UsuarioMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PacientesServiceImpl implements PacientesService {

    private final PacientesRepository pacientesRepository;
    private final PacientesMapper pacientesMapper;
    private final TerapeutasRepository terapeutasRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    // CREATE
    @Override
    public PacientesDto createPacientes(PacientesDto pacientesDto) {
        // Validar que el usuario existe y no es ya paciente
        Usuario usuario = usuarioRepository.findById(pacientesDto.getUsuario().getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (pacientesRepository.existsByUsuarioId(usuario.getId())) {
            throw new IllegalStateException("Este usuario ya está registrado como paciente");
        }

        // Validar que el terapeuta existe
        Terapeutas terapeuta = terapeutasRepository.findById(pacientesDto.getTerapeutaId())
                .orElseThrow(() -> new EntityNotFoundException("Terapeuta no encontrado"));

        Pacientes paciente = pacientesMapper.pacientesDtoToPacientes(pacientesDto);
        paciente.setUsuario(usuario);
        paciente.setTerapeuta(terapeuta);

        Pacientes savedPaciente = pacientesRepository.save(paciente);
        return buildCompletePacienteDto(savedPaciente);
    }

    // SELECT BY ID
    @Override
    public PacientesDto getPacientes(Integer id) {
        Pacientes paciente = pacientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un pacientes con ese ID"));
        return buildCompletePacienteDto(paciente);
    }

    // SELECT BY USUARIO ID
    @Override
    public PacientesDto getPacienteByUsuarioId(Integer usuarioId) {
        Pacientes paciente = pacientesRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado para este usuario"));
        return buildCompletePacienteDto(paciente);
    }

    // SELECT ALL
    @Override
    public List<PacientesDto> getAllPacientes() {
        return pacientesRepository.findAll().stream()
                .map(this::buildCompletePacienteDto)
                .collect(Collectors.toList());
    }

    // SELECT PACIENTES BY TERAPEUTA
    @Override
    public List<PacientesDto> getPacientesByTerapeuta(Integer terapeutaId) {
        return pacientesRepository.findByTerapeutaId(terapeutaId).stream()
                .map(this::buildCompletePacienteDto)
                .collect(Collectors.toList());
    }

    @Override
    public PacientesDto updatePacientes(Integer id, PacientesDto pacientesDto) {
        Pacientes paciente = pacientesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        // Actualizar campos básicos
        paciente.setFechaAcv(pacientesDto.getFechaAcv());
        paciente.setTipoAcv(pacientesDto.getTipoAcv());
        paciente.setAntecedentes(pacientesDto.getAntecedentes());
        paciente.setMedicacionActual(pacientesDto.getMedicacionActual());

        // Campos que podrían tener lógica adicional
        updateProgreso(paciente, pacientesDto.getProgresoTotal());
        updateEjerciciosCompletados(paciente, pacientesDto.getEjerciciosCompletados());
        updateDiasConsecutivos(paciente, pacientesDto.getDiasConsecutivos());

        // Actualizar terapeuta si es diferente
        if (!paciente.getTerapeuta().getId().equals(pacientesDto.getTerapeutaId())) {
            Terapeutas nuevoTerapeuta = terapeutasRepository.findById(pacientesDto.getTerapeutaId())
                    .orElseThrow(() -> new EntityNotFoundException("Nuevo terapeuta no encontrado"));
            paciente.setTerapeuta(nuevoTerapeuta);
        }

        Pacientes updatedPaciente = pacientesRepository.save(paciente);
        return buildCompletePacienteDto(updatedPaciente);
    }


    // DELETE BY ID
    @Override
    public void deletePacientesById(Integer id) {
        Pacientes paciente = pacientesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
        pacientesRepository.delete(paciente);
    }

    // UPDATE PROGRESO PACIENTE
    @Override
    public PacientesDto updateProgresoPaciente(Integer id, BigDecimal nuevoProgreso) {
        Pacientes paciente = pacientesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        updateProgreso(paciente, nuevoProgreso);

        return buildCompletePacienteDto(pacientesRepository.save(paciente));
    }

    // Métodos auxiliares privados
    private PacientesDto buildCompletePacienteDto(Pacientes paciente) {
        PacientesDto dto = pacientesMapper.pacientesToPacientesDto(paciente);
        dto.setUsuario(usuarioMapper.usuarioToUsuarioDto(paciente.getUsuario()));
        dto.setTerapeutaNombre(
                paciente.getTerapeuta().getUsuario().getNombre() + " " +
                        paciente.getTerapeuta().getUsuario().getApellido()
        );
        return dto;
    }

    private void updateProgreso(Pacientes paciente, BigDecimal nuevoProgreso) {
        if (nuevoProgreso.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El progreso no puede ser negativo");
        }
        if (nuevoProgreso.compareTo(new BigDecimal("100.00")) > 0) {
            throw new IllegalArgumentException("El progreso no puede ser mayor a 100");
        }
        paciente.setProgresoTotal(nuevoProgreso);
    }

    private void updateEjerciciosCompletados(Pacientes paciente, Integer ejercicios) {
        if (ejercicios < 0) {
            throw new IllegalArgumentException("Los ejercicios completados no pueden ser negativos");
        }
        paciente.setEjerciciosCompletados(ejercicios);
    }

    private void updateDiasConsecutivos(Pacientes paciente, Integer dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Los días consecutivos no pueden ser negativos");
        }
        paciente.setDiasConsecutivos(dias);
    }
}
