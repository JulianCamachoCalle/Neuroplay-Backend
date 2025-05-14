package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;
import dev.juliancamacho.neuroplay.modelo.entity.Pacientes;
import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacientesMapper {

    // Mapeo de Entidad -> DTO
    @Mapping(target = "usuario", source = "usuario")
    PacientesDto pacientesToPacientesDto(Pacientes pacientes);

    // Mapeo de DTO -> Entidad
    @Mapping(target = "terapeuta", ignore = true)
    Pacientes pacientesDtoToPacientes(PacientesDto pacientesDto);
}
