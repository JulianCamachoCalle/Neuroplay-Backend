package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.ProgresoDto;
import dev.juliancamacho.neuroplay.modelo.entity.Progreso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PacientesMapper.class})
public interface ProgresoMapper {

    @Mapping(target = "pacienteId", source = "paciente.id")
    @Mapping(target = "pacienteNombre", expression = "java(progreso.getPaciente().getUsuario().getNombreCompleto())")
    ProgresoDto progresoToProgresoDto(Progreso progreso);

    @Mapping(target = "paciente", ignore = true)
    Progreso progresoDtoToProgreso(ProgresoDto progresoDto);
}
