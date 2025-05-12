package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.UsuarioDto;
import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Mapeo de Entidad -> DTO
    @Mapping(target = "password", ignore = true)
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    // Mapeo de DTO -> Entidad
    @Mapping(target = "terapeutaInfo", ignore = true)
    @Mapping(target = "pacienteInfo", ignore = true)
    @Mapping(target = "forgotPassword", ignore = true)
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
}
