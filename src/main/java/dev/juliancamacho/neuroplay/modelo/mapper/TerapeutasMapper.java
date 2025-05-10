package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.TerapeutasDto;
import dev.juliancamacho.neuroplay.modelo.entity.Terapeutas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TerapeutasMapper {

    // Mapeo de Entidad -> DTO
    TerapeutasDto terapeutasToTerapeutasDto(Terapeutas terapeutas);

    // Mapeo de DTO -> Entidad
    Terapeutas terapeutasDtoToTerapeutas(TerapeutasDto terapeutasDto);
}
