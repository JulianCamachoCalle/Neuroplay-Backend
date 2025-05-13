package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.SesionDto;
import dev.juliancamacho.neuroplay.modelo.entity.Sesion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PacientesMapper.class, EjercicioMapper.class})
public interface SesionMapper {

    @Mapping(target = "ejercicioId", source = "ejercicio.id")
    @Mapping(target = "ejercicioNombre", source = "ejercicio.nombre")
    @Mapping(target = "pacienteId", source = "paciente.id")
    @Mapping(target = "pacienteNombre", expression = "java(sesion.getPaciente().getUsuario().getNombreCompleto())")
    SesionDto sesionToSesionDto(Sesion sesion);

    @Mapping(target = "ejercicio", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    Sesion sesionDtoToSesion(SesionDto sesionDto);
}
