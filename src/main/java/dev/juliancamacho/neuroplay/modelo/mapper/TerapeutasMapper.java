package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.TerapeutasDto;
import dev.juliancamacho.neuroplay.modelo.entity.Terapeutas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TerapeutasMapper {

    // Mapeo de Entidad -> DTO
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "pacientesIds", expression = "java(terapeutas.getPacientes().stream().map(p -> p.getId()).collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "cantidadPacientes", expression = "java(terapeutas.getPacientes().size())")
    TerapeutasDto terapeutasToTerapeutasDto(Terapeutas terapeutas);

    // Mapeo de DTO -> Entidad
    @Mapping(target = "pacientes", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Terapeutas terapeutasDtoToTerapeutas(TerapeutasDto terapeutasDto);
}
