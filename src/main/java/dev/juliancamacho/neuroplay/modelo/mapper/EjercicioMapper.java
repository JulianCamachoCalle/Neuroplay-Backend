package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.EjercicioResumenDto;
import dev.juliancamacho.neuroplay.modelo.dto.EjerciciosDto;
import dev.juliancamacho.neuroplay.modelo.entity.Ejercicios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EjercicioMapper {

    @Mapping(target = "terapiaId", source = "terapia.id")
    @Mapping(target = "terapiaNombre", source = "terapia.nombre")
    EjerciciosDto ejercicioToEjercicioDto(Ejercicios ejercicio);

    @Mapping(target = "id", ignore = true) // Ignorar el ID en la creación
    @Mapping(target = "terapia", ignore = true)
    Ejercicios ejercicioDtoToEjercicio(EjerciciosDto ejercicioDto);
}
