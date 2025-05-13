package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.EjercicioResumenDto;
import dev.juliancamacho.neuroplay.modelo.dto.TerapiaDto;
import dev.juliancamacho.neuroplay.modelo.entity.Ejercicios;
import dev.juliancamacho.neuroplay.modelo.entity.Terapias;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface TerapiaMapper {

    @Mapping(target = "pacienteId", source = "paciente.id")
    @Mapping(target = "pacienteNombre", expression = "java(terapia.getPaciente().getUsuario().getNombreCompleto())")
    @Mapping(target = "terapeutaId", source = "terapeuta.id")
    @Mapping(target = "terapeutaNombre", expression = "java(terapia.getTerapeuta().getUsuario().getNombreCompleto())")
    @Mapping(target = "ejercicios", ignore = true) // Se carga aparte
    TerapiaDto terapiaToTerapiaDto(Terapias terapia);

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "terapeuta", ignore = true)
    Terapias terapiaDtoToTerapia(TerapiaDto terapiaDto);

    default List<EjercicioResumenDto> mapEjercicios(List<Ejercicios> ejercicios) {
        return ejercicios.stream()
                .map(this::toEjercicioResumenDto)
                .collect(Collectors.toList());
    }

    default EjercicioResumenDto toEjercicioResumenDto(Ejercicios ejercicio) {
        EjercicioResumenDto dto = new EjercicioResumenDto();
        dto.setId(ejercicio.getId());
        dto.setNombre(ejercicio.getNombre());
        dto.setTipo(ejercicio.getTipo().name());
        dto.setNivelDificultad(ejercicio.getNivelDificultad().name());
        return dto;
    }
}
