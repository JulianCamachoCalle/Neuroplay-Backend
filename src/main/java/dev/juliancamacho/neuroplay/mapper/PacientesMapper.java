package dev.juliancamacho.neuroplay.mapper;

import dev.juliancamacho.neuroplay.dto.PacientesDto;
import dev.juliancamacho.neuroplay.entity.Pacientes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacientesMapper {

    // Mapeo de Entidad -> DTO
    PacientesDto pacientesToPacientesDto(Pacientes pacientes);

    // Mapeo de DTO -> Entidad
    Pacientes pacientesDtoToPacientes(PacientesDto pacientesDto);
}
