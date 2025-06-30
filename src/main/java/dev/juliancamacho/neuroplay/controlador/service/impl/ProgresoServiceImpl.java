package dev.juliancamacho.neuroplay.controlador.service.impl;

import dev.juliancamacho.neuroplay.controlador.repository.PacientesRepository;
import dev.juliancamacho.neuroplay.controlador.repository.ProgresoRepository;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.ProgresoService;
import dev.juliancamacho.neuroplay.modelo.dto.ProgresoDto;
import dev.juliancamacho.neuroplay.modelo.entity.Pacientes;
import dev.juliancamacho.neuroplay.modelo.entity.Progreso;
import dev.juliancamacho.neuroplay.modelo.mapper.ProgresoMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgresoServiceImpl implements ProgresoService {

    private final ProgresoRepository progresoRepository;
    private final ProgresoMapper progresoMapper;
    private final PacientesRepository pacienteRepository;

    @Override
    public ProgresoDto registrarProgreso(ProgresoDto progresoDto) {
        Pacientes paciente = pacienteRepository.findById(progresoDto.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        Progreso progreso = progresoMapper.progresoDtoToProgreso(progresoDto);
        progreso.setPaciente(paciente);

        Progreso savedProgreso = progresoRepository.save(progreso);
        return progresoMapper.progresoToProgresoDto(savedProgreso);
    }

    @Override
    public List<ProgresoDto> getAllProgresos() {
        return progresoRepository.findAll().stream().map(progresoMapper::progresoToProgresoDto).collect(Collectors.toList());
    }

    @Override
    public ProgresoDto getProgresoById(Integer id) {
        Progreso progreso = progresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de progreso no encontrado"));
        return progresoMapper.progresoToProgresoDto(progreso);
    }

    @Override
    public List<ProgresoDto> getProgresoByPaciente(Integer pacienteId) {
        return progresoRepository.findByPacienteIdOrderByFechaDesc(pacienteId).stream()
                .map(progresoMapper::progresoToProgresoDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgresoDto actualizarProgreso(Integer id, ProgresoDto progresoDto) {
        Progreso progreso = progresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de progreso no encontrado"));

        // Actualización manual de campos
        progreso.setFecha(progresoDto.getFecha());
        progreso.setFuerza(progresoDto.getFuerza());
        progreso.setMovilidad(progresoDto.getMovilidad());
        progreso.setDetalle(progresoDto.getDetalle());
        progreso.setNotas(progresoDto.getNotas());

        // No actualizamos pacienteId directamente
        Progreso updatedProgreso = progresoRepository.save(progreso);
        return progresoMapper.progresoToProgresoDto(updatedProgreso);
    }

    @Override
    public void eliminarProgreso(Integer id) {
        Progreso progreso = progresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de progreso no encontrado"));
        progresoRepository.delete(progreso);
    }

    @Override
    public Map<String, Object> getEstadisticasProgreso(Integer pacienteId) {
        List<Progreso> progresos = progresoRepository.findByPacienteId(pacienteId);

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalRegistros", progresos.size());
        stats.put("ultimoRegistro", progresos.stream()
                .findFirst()
                .map(progresoMapper::progresoToProgresoDto)
                .orElse(null));

        return stats;
    }

    @Override
    public ProgresoDto registrarProgresoTerapia(Integer pacienteId, BigDecimal detalle, String notas) {
        Pacientes paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        ProgresoDto progresoDto = new ProgresoDto();
        progresoDto.setPacienteId(pacienteId);
        progresoDto.setPacienteNombre(paciente.getUsuario().getNombre() + " " + paciente.getUsuario().getApellido());
        progresoDto.setFecha(new Date());
        progresoDto.setDetalle(detalle); // Usamos el campo detalle para el porcentaje de progreso
        progresoDto.setNotas(notas);

        Progreso progresoEntity = progresoMapper.progresoDtoToProgreso(progresoDto);
        progresoEntity.setPaciente(paciente);
        Progreso savedProgreso = progresoRepository.save(progresoEntity);

        return progresoMapper.progresoToProgresoDto(savedProgreso);
    }

    @Override
    public ProgresoDto registrarSesionCompletada(Integer pacienteId) {
        return this.registrarProgresoTerapia(pacienteId, new BigDecimal(100), "Sesión completada");
    }

    @Override
    public List<ProgresoDto> getProgresoByTerapia(Integer pacienteId) {
        return progresoRepository.findByPacienteIdOrderByFechaDesc(pacienteId).stream()
                .map(progreso -> {
                    ProgresoDto dto = progresoMapper.progresoToProgresoDto(progreso);
                    dto.setPacienteNombre(progreso.getPaciente().getUsuario().getNombre() + " " +
                            progreso.getPaciente().getUsuario().getApellido());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
