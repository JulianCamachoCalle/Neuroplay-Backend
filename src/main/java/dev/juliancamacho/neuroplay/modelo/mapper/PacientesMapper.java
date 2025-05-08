package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;
import dev.juliancamacho.neuroplay.modelo.entity.Pacientes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacientesMapper {

    // Mapeo de Entidad -> DTO
    PacientesDto pacientesToPacientesDto(Pacientes pacientes);

    // Mapeo de DTO -> Entidad
    Pacientes pacientesDtoToPacientes(PacientesDto pacientesDto);
}
