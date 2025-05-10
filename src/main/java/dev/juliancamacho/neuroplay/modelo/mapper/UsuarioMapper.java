package dev.juliancamacho.neuroplay.modelo.mapper;

import dev.juliancamacho.neuroplay.modelo.dto.UsuarioDto;
import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Mapeo de Entidad -> DTO
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    // Mapeo de DTO -> Entidad
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
}
