package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;
import dev.juliancamacho.neuroplay.modelo.entity.Pacientes;
import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacientesMapper {

    // Mapeo de Entidad -> DTO
    @Mapping(target = "terapeutaNombre", expression = "java(pacientes.getTerapeuta().getUsuario().getNombreCompleto())")
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "terapeutaId", source = "terapeuta.id")
    PacientesDto pacientesToPacientesDto(Pacientes pacientes);

    // Mapeo de DTO -> Entidad
    @Mapping(target = "terapeuta", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Pacientes pacientesDtoToPacientes(PacientesDto pacientesDto);

    // Metodo para obtener el nombre completo (si no existe en la entidad)
    default String getNombreCompleto(Usuario usuario) {
        return usuario.getNombre() + " " + usuario.getApellido();
    }
}
