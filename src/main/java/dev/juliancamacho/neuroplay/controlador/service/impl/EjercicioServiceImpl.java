package dev.juliancamacho.neuroplay.controlador.service.impl;

import dev.juliancamacho.neuroplay.controlador.repository.EjercicioRepository;
import dev.juliancamacho.neuroplay.controlador.repository.TerapiaRepository;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.EjercicioService;
import dev.juliancamacho.neuroplay.modelo.dto.EjerciciosDto;
import dev.juliancamacho.neuroplay.modelo.entity.Ejercicios;
import dev.juliancamacho.neuroplay.modelo.entity.Terapias;
import dev.juliancamacho.neuroplay.modelo.enums.NivelDificultad;
import dev.juliancamacho.neuroplay.modelo.enums.TipoEjercicio;
import dev.juliancamacho.neuroplay.modelo.mapper.EjercicioMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EjercicioServiceImpl implements EjercicioService {

    private final EjercicioRepository ejercicioRepository;
    private final EjercicioMapper ejercicioMapper;
    private final TerapiaRepository terapiaRepository;

    @Override
    public EjerciciosDto createEjercicio(EjerciciosDto ejercicioDto) {
        Terapias terapia = terapiaRepository.findById(ejercicioDto.getTerapiaId())
                .orElseThrow(() -> new EntityNotFoundException("Terapia no encontrada"));

        Ejercicios ejercicio = ejercicioMapper.ejercicioDtoToEjercicio(ejercicioDto);
        ejercicio.setTerapia(terapia);

        Ejercicios savedEjercicio = ejercicioRepository.save(ejercicio);
        return ejercicioMapper.ejercicioToEjercicioDto(savedEjercicio);
    }

    @Override
    public EjerciciosDto getEjercicioById(Integer id) {
        Ejercicios ejercicio = ejercicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado"));
        return ejercicioMapper.ejercicioToEjercicioDto(ejercicio);
    }

    @Override
    public List<EjerciciosDto> getEjerciciosByTerapia(Integer terapiaId) {
        return ejercicioRepository.findByTerapiaId(terapiaId).stream()
                .map(ejercicioMapper::ejercicioToEjercicioDto)
                .collect(Collectors.toList());
    }

    @Override
    public EjerciciosDto updateEjercicio(Integer id, EjerciciosDto ejercicioDto) {
        Ejercicios ejercicio = ejercicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado"));

        // Actualización manual de campos
        ejercicio.setNombre(ejercicioDto.getNombre());
        ejercicio.setDescripcion(ejercicioDto.getDescripcion());
        ejercicio.setTipo(TipoEjercicio.valueOf(ejercicioDto.getTipo()));
        ejercicio.setRepeticiones(ejercicioDto.getRepeticiones());
        ejercicio.setDuracionMinutos(ejercicioDto.getDuracionMinutos());
        ejercicio.setNivelDificultad(NivelDificultad.valueOf(ejercicioDto.getNivelDificultad()));

        // No actualizamos terapiaId directamente, se mantiene la relación original
        Ejercicios updatedEjercicio = ejercicioRepository.save(ejercicio);
        return ejercicioMapper.ejercicioToEjercicioDto(updatedEjercicio);
    }

    @Override
    public void deleteEjercicio(Integer id) {
        Ejercicios ejercicio = ejercicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado"));
        ejercicioRepository.delete(ejercicio);
    }

    @Override
    public List<EjerciciosDto> filterEjerciciosByTipo(String tipo) {
        TipoEjercicio tipoEjercicio = TipoEjercicio.valueOf(tipo.toUpperCase());
        return ejercicioRepository.findByTipo(tipoEjercicio).stream()
                .map(ejercicioMapper::ejercicioToEjercicioDto)
                .collect(Collectors.toList());
    }
}
