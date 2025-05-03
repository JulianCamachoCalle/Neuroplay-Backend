package dev.juliancamacho.neuroplay.service.impl;

import dev.juliancamacho.neuroplay.dto.PacientesDto;
import dev.juliancamacho.neuroplay.entity.Pacientes;
import dev.juliancamacho.neuroplay.mapper.PacientesMapper;
import dev.juliancamacho.neuroplay.repository.PacientesRepository;
import dev.juliancamacho.neuroplay.service.interfaces.PacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacientesServiceImpl implements PacientesService {

    @Autowired
    private PacientesRepository pacientesRepository;

    @Autowired
    private PacientesMapper pacientesMapper;

    // CREATE
    @Override
    public PacientesDto createPacientes(PacientesDto pacientesDto) {
        Pacientes pacientes = pacientesMapper.pacientesDtoToPacientes(pacientesDto);
        Pacientes savedPacientes = pacientesRepository.save(pacientes);
        return pacientesMapper.pacientesToPacientesDto(savedPacientes);
    }

    // SELECT BY ID
    @Override
    public PacientesDto getPacientes(Integer id) {
        Pacientes pacientes = pacientesRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe un pacientes con ese ID"));
        return pacientesMapper.pacientesToPacientesDto(pacientes);
    }

    // SELECT ALL
    @Override
    public List<PacientesDto> getAllPacientes() {
        List<Pacientes> pacientess = pacientesRepository.findAll();

        return pacientess.stream().map(
                pacientesMapper::pacientesToPacientesDto).collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public PacientesDto updatePacientes(Integer id, PacientesDto pacientesDto) {
        Pacientes pacientes = pacientesRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe un pacientes con ese ID"));

        pacientes.setTipoAcv(pacientesDto.getTipoAcv());
        pacientes.setAntecedentes(pacientesDto.getAntecedentes());
        pacientes.setMedicacionActual(pacientesDto.getMedicacionActual());
        pacientes.setProgresoActual(pacientesDto.getProgresoActual());
        pacientes.setEjerciciosCompletados(pacientesDto.getEjerciciosCompletados());
        pacientes.setDiasConsecutivos(pacientesDto.getDiasConsecutivos());

        Pacientes updatedPacientes = pacientesRepository.save(pacientes);

        return pacientesMapper.pacientesToPacientesDto(updatedPacientes);
    }

    // DELETE BY ID
    @Override
    public void deletePacientesById(Integer id) {
        pacientesRepository.deleteById(id);
    }
}
